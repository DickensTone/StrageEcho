package com.echo.client.schedule;

import com.echo.client.schedule.dumpInterface.DumpAgent;
import com.echo.client.service.transportLog.WriteWorker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;


/**
 *  Start the ScheduleTask to write the Log in the database.
 */

public class WriteAgent implements DumpAgent {

    @Autowired
    private WriteWorker writeWorker;

    private volatile Future<?> future;

    private ReentrantLock lock = new ReentrantLock();

    private WriteAgent(){

    }

    /**
     *
     * start the ScheduleTask
     * We guarantee that the ScheduleTask's number <= 1
     * @return
     */
    @Override
    public Future<?> start(){

        lock.lock();
        if(isRunning()){
            return null;
        }
        ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(1, (r) -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });

        // get the Future that.It will be stopped in the worker thread.
        Future<?> future =  schedule.scheduleWithFixedDelay(
                writeWorker,
                1000,
                1000,
                TimeUnit.MILLISECONDS);

        writeWorker.setFuture(future);
        this.future =future;
        lock.unlock();
        return future;
    }

    /**
     * return a state.
     * True:there is a scheduleTask is running.
     * False: this no scheduleTask is running.
     * @return
     */
    public boolean isRunning(){
        if(this.future == null){
            return false;
        }
        if(this.future.isCancelled()){
            return false;
        }

        return true;
    }

    public static WriteAgent getInstance(){
        return Loader.writeAgent;
    }

    private static class Loader {
        private static final WriteAgent writeAgent = new WriteAgent();

    }

}
