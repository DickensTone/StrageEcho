package com.echo.nacosExample;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hello.nacos")
@Data
public class TestConfig {
    private String name;
}
