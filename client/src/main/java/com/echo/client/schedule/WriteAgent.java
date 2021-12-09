package com.echo.client.schedule;

import com.echo.client.service.WriteWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 *  Start the ScheduleTask to write the Log in the database.
 */
@Service
public class WriteAgent {

    @Autowired
    private WriteWorker writeWorker;

    /**
     *
     * start the ScheduleTask
     */
    public Future<?> start(){
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);

        // get the Future that.It will be stopped in the worker thread.
        Future<?> future =  schedule.scheduleWithFixedDelay(
                writeWorker,
                1000,
                1000,
                TimeUnit.MILLISECONDS);

        writeWorker.setFuture(future);

        return future;

    }


}
