package com.echo.server.configuration;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.EnableNacos;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableNacos(globalProperties = @NacosProperties(serverAddr = "${nacos.server-addr}"))
public class NacosConfig {
    
}
