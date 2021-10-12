package com.echo.server.utils;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.echo.server.config.NacosServerConfig;

import java.util.Properties;

public class EchoNcosUtils {
    public static String getProperties(String dataId, String group, String addr, NacosServerConfig nacosServerConfig) throws NacosException {
        String serverAddr = nacosServerConfig.addr();
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, NacosServerConfig.localhost.timeout());
        return content;
    }
}
