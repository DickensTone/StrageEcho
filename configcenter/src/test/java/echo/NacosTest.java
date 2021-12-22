package echo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.echo.configcenter.config.NacosServerConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

/**
 * Test if the nacos server is working.
 * Test the nacos's normal Function:
 * 1.PUSH
 * 2.GET
 * 3.DELETE
 */
public class NacosTest {
    private ConfigService configService;
    private final String dataId = "com-echo-Test";
    private final String group = "example";


    /**
     * Publish Config
     */
    @Before
    public void pushConfig(){
        try {
            // 初始化配置服务，控制台通过示例代码自动获取下面参数
            String serverAddr = NacosServerConfig.localhost.addr();
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            configService = NacosFactory.createConfigService(properties);
            boolean isPublishOk = configService.publishConfig(dataId, group, "content");
            Assert.assertTrue(isPublishOk);
        } catch (NacosException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    @Test
    public void GetConfig(){
        getProperties();
    }

    /**
     * Delete Config
     */
    @After
    public void deleteConfig(){
        try {
            boolean isRemoveOk = configService.removeConfig(dataId, group);
            Assert.assertTrue(isRemoveOk);
        } catch (NacosException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Get Config from Nacos.
     */
    private void getProperties() {
        try {
            Thread.sleep(300);
            String content = configService.getConfig(dataId, group, NacosServerConfig.localhost.timeout());
            Assert.assertEquals("content",content);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }


}
