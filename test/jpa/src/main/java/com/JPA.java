package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class JPA {
    public static void main(String[] args) {
        SpringApplication.run(JPA.class);
    }
}
