package utils;

import com.echo.core.utils.PropertiesUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtilTest {
    @Test
    public void testRead(){
        String path = "test.properties";
        try {
            Properties properties = PropertiesUtil.getProperties(path);
            Assert.assertEquals(properties.size(), 2);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
}
