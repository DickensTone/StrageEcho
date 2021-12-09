package com.echo.client.Write;

import com.echo.client.schedule.WriteAgent;
import com.echo.client.service.WriteQueue;
import com.echo.client.service.WriteWorker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WriteQueueTest {
    @Autowired
    private WriteQueue writeQueue;

    @Autowired
    private WriteWorker writeWorker;

    @Autowired
    private WriteAgent writeAgent;

    @Test
    public void add() throws InterruptedException {
        writeQueue.addContent(new StringBuffer("123"));
        writeQueue.addContent(new StringBuffer("456"));
        writeQueue.addContent(new StringBuffer("789"));
        writeQueue.addContent(new StringBuffer("101112"));

        Future<?> future = writeAgent.start();

        while(!writeWorker.isCanceled()){
            Thread.sleep(2000);
        }

        Assert.assertTrue(future.isCancelled());
        Assert.assertFalse(writeQueue.needDump());
    }


}
