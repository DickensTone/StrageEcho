package com.echo.client.Write;

import com.echo.client.repository.ServiceLog;
import com.echo.client.schedule.WriteAgent;
import com.echo.client.service.transportLog.WriteQueue;
import com.echo.client.service.transportLog.WriteWorker;
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

    @Autowired
    private ServiceLog serviceLog;

    @Test
    public void add() throws InterruptedException {
        serviceLog.deleteAll();
        writeQueue.registerListener(writeAgent);
        writeQueue.addContent(new StringBuffer("1"));
        writeQueue.addContent(new StringBuffer("2"));
        writeQueue.addContent(new StringBuffer("3"));
        writeQueue.addContent(new StringBuffer("4"));

        while(!writeWorker.isCanceled()){
            Thread.sleep(2000);
        }

        Assert.assertFalse(writeAgent.isRunning());
        Assert.assertFalse(writeQueue.needDump());
    }


}
