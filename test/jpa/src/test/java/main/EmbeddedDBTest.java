package main;

import com.ClientApplication;
import com.domain.ServiceLog;
import com.domain.Transport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ClientApplication.class)
public class EmbeddedDBTest {
    @Autowired
    private ServiceLog serviceLog;


    @Test
    public void test1(){
        serviceLog.save(new Transport());
    }
}
