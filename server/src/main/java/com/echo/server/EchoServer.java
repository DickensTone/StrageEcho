package com.echo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(EchoServerProperties.class)
public class EchoServer {
    public static void main(String[] args) {
        SpringApplication.run(EchoServer.class, args);
    }
}

