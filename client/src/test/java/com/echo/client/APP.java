package com.echo.client;

import com.echo.client.domain.Transport;
import com.echo.client.repository.ServiceLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Optional;

@SpringBootTest
public class APP {

    @Autowired
    private ServiceLog serviceLog;

    @Test
    public void UserJPATest(){

        String id = "90087";

        Optional<Transport> userOptional = serviceLog.findById(id);
        userOptional.ifPresent(u->serviceLog.deleteById(id));
        Transport transport = new Transport();
        transport.setModifyTime(Instant.now());
        transport.setCreateTime(Instant.now());
        transport = serviceLog.save(transport);

        userOptional = serviceLog.findById(transport.getId());
        serviceLog.delete(transport);
        Assertions.assertTrue(userOptional.isPresent());
        Assertions.assertEquals(transport.getId(), userOptional.get().getId());
    }




}
