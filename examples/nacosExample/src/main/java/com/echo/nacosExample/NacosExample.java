package com.echo.nacosExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TestConfig.class)
public class NacosExample {
    public static void main(String[] args) {
        SpringApplication.run(NacosExample.class, args);
    }
}