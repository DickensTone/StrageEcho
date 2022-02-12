package main;

import com.domain.Transport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.JPA;
import com.domain.ServiceRepository;

@SpringBootTest(classes = JPA.class)
public class EmbeddedDBTest {
    @Autowired
    private ServiceRepository serviceRepository;


    @Test
    public void test1(){
        serviceRepository.save(new Transport());
    }
}
