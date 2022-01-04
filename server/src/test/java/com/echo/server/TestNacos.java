package com.echo.server;


import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.echo.server.configuration.NacosConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.alibaba.nacos.api.common.Constants.DEFAULT_GROUP;
import static com.alibaba.nacos.client.config.common.ConfigConstants.DATA_ID;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NacosConfig.class)
public class TestNacos {

    @NacosInjected
    private ConfigService configService;
    
    
    @Test
    public void test() throws NacosException {
        configService.publishConfig(DATA_ID, DEFAULT_GROUP, "9527");
    }
    
}
