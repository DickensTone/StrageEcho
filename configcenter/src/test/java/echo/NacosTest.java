package echo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.echo.configcenter.config.NacosServerConfig;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class NacosTest {

    @Test
    public void pushAndGet(){
        pushProperties();
        getProperties();
    }

    private void getProperties() {
        try {
            String serverAddr = NacosServerConfig.localhost.addr();
            String dataId = "com-echo-EchoServer";
            String group = "example";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, NacosServerConfig.localhost.timeout());
            Assert.assertEquals("content",content);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public  void pushProperties(){
        try {
            // 初始化配置服务，控制台通过示例代码自动获取下面参数
            String serverAddr = NacosServerConfig.localhost.addr();
            String dataId = "com-echo-EchoServer";
            String group = "example";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            boolean isPublishOk = configService.publishConfig(dataId, group, "content");
            Assert.assertEquals(true, isPublishOk);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
