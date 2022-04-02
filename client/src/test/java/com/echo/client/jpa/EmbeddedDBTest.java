package com.echo.client.jpa;


import com.echo.client.config.databaseConfig.DataBaseContextHolder;
import com.echo.client.demo.Greet;
import com.echo.client.demo.HelloWorld;
import com.echo.client.domain.Transport;
import com.echo.client.enums.DataBaseType;
import com.echo.client.repository.ServiceLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmbeddedDBTest {
    @Autowired
    private ServiceLog serviceLog;


    @Test
    public void test1(){
        DataBaseContextHolder.set(DataBaseType.External);
        serviceLog.save(new Transport());
    }


}
