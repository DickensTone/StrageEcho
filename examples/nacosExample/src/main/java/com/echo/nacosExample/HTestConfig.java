package com.echo.nacosExample;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "user")
@Component
@Data
public class HTestConfig {
    private Map<String, String> names;
}
