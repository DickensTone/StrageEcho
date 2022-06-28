package com.echo.server;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "echo.server")
@Data
public class EchoServerProperties {
    private Integer port;
    private String name;
}
