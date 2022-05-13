package com.echo.client.config.databaseConfig;

import com.echo.client.domain.Transport;
import com.echo.client.enums.DataBaseType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories("com.echo.client.repository")
@EnableTransactionManagement
public class JpaDataBaseConfiguration {
    @Bean
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder().//
                setType(EmbeddedDatabaseType.HSQL).//
                setName("service-log").//
                build();
    }

    @Bean
    public DataSource externalDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/usr_info?createDatabaseIfNotExist=true");
        return dataSource;

    }

    @Bean
    public DataSource dynamicDataSource(){
        Map<Object, Object> targetDataSources = new HashMap<>();
        DataSource embeddedDataSource = embeddedDataSource();
        DataSource externalDataSource = externalDataSource();
        targetDataSources.put(DataBaseType.Embedded,
                embeddedDataSource);
        targetDataSources.put(DataBaseType.External,
                externalDataSource);

        DataSourceRouter dynamicDataSource
                = new DataSourceRouter();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(embeddedDataSource);
        return dynamicDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(Transport.class.getPackage().getName());
        factory.setDataSource(dynamicDataSource());
//        factory.setJpaProperties(new Properties(){{
//            setProperty("spring.jpa.hibernate.naming.physical-strategy","org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
//        }});
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
