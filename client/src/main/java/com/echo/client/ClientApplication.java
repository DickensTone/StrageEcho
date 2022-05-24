package com.echo.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class  ClientApplication {
    public static void main(String[] args) {
        log.info("first logback");
        SpringApplication.run(ClientApplication.class);
    }
}
