package com.echo.client;

import com.echo.client.Write.WriteQueueTest;
import com.echo.client.domain.Transport;
import com.echo.client.repository.ServiceLog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.TransferQueue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class APP {

    @Autowired
    private ServiceLog serviceLog;



    @Test
    public void UserJPATest(){
        Transport transport = new Transport();
        String id = "90087";

        Optional<Transport> userOptional = serviceLog.findById(id);
        userOptional.ifPresent(u->serviceLog.deleteById(id));


        transport.setModifyTime(Instant.now());
        transport.setCreateTime(Instant.now());
        transport = serviceLog.save(transport);

        userOptional = serviceLog.findById(transport.getId());
        Assert.assertTrue(userOptional.isPresent());
        Assert.assertEquals(transport.getId(), userOptional.get().getId());
    }




}
