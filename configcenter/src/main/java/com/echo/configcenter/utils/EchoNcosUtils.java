package com.echo.configcenter.utils;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.echo.configcenter.config.NacosServerConfig;

import java.util.Properties;

public class EchoNcosUtils {
    public ConfigService getConfigService(String dataId, String group, NacosServerConfig nacosServerConfig) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", nacosServerConfig.addr());
        return NacosFactory.createConfigService(properties);
    }

}
