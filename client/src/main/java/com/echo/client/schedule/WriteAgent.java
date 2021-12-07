package com.echo.client.schedule;

import com.echo.client.service.WriteWorker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class WriteAgent {

    @Autowired
    private WriteWorker writeWorker;

    ReentrantLock reentrantLock = new ReentrantLock();

    public void handle(){
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);

        Future future =  schedule.scheduleWithFixedDelay(
                writeWorker,
                0,
                1000,
                TimeUnit.MILLISECONDS);

        writeWorker.setFuture(future);

    }


}
