package com.echo.server;


import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.echo.server.configuration.NacosConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static com.alibaba.nacos.api.common.Constants.DEFAULT_GROUP;
import static com.alibaba.nacos.client.config.common.ConfigConstants.DATA_ID;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NacosConfig.class)
public class TestNacos {

    @NacosInjected
    private ConfigService configService;

    @NacosValue(value = "${uuid:helloBug}", autoRefreshed = true)
    private String uuid;

    @NacosValue(value = "${server.port:8787}", autoRefreshed = true)
    private int port;

    @NacosValue(value = "${server.address:localhost}", autoRefreshed = true)
    private String addr;
    
    
    
    @Test
    public void test() throws NacosException, InterruptedException {
        Assert.assertNotEquals(uuid, "helloBug");
        String content = UUID.randomUUID().toString();
        configService.publishConfig(DATA_ID, DEFAULT_GROUP, "uuid="+content);
        
        //give nacos some time to save the published content.
        Thread.sleep(1000);
        String nowContent = configService.getConfig(DATA_ID, DEFAULT_GROUP, 200);
        nowContent = nowContent.split("=")[1];

        Assert.assertEquals(content, nowContent);
        Assert.assertEquals(uuid, content);
    }
    
    @Test
    public void test1() {
        System.out.println(addr);
        System.out.println(port);
    }
}
