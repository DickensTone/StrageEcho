package com.echo.server.configuration;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.EnableNacos;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableNacos(globalProperties = @NacosProperties(serverAddr = "${nacos.server-addr}"))
@NacosPropertySource(dataId = "server_application", autoRefreshed = true)
@NacosPropertySource(dataId = "dataId", autoRefreshed = true)
public class NacosConfig {
    
}
