package echo;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.echo.configcenter.config.NacosConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.alibaba.nacos.api.common.Constants.DEFAULT_GROUP;
import static com.alibaba.nacos.client.config.common.ConfigConstants.DATA_ID;

@ContextConfiguration(classes=NacosConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class NacosInjectTest {
    @NacosInjected
    private ConfigService configService;

    @Test
    public void testPublishConfig() throws NacosException {
        configService.publishConfig(DATA_ID, DEFAULT_GROUP, "9527");
    }
}
