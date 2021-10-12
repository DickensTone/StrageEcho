import com.echo.server.config.NacosServerConfig;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ClassForTest {

    @Test
    public void test() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> driverImplClass = Class.forName("com.echo.configcenter.config.NacosServerConfig");
        Constructor constructor = driverImplClass.getConstructor(Properties.class);
        Properties properties = new Properties();
        NacosServerConfig vendorImpl = (NacosServerConfig) constructor.newInstance(properties);
        System.out.println(vendorImpl);

    }
}
