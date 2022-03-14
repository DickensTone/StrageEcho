package com.echo.client.schedule;

import com.echo.client.schedule.dumpInterface.DumpAgent;
import com.echo.client.service.transportLog.WriteWorker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;


/**
 *  Start the ScheduleTask to write the Log in the database.
 */

public class LogWriteAgent implements DumpAgent {

    private WriteWorker writeWorker;

    private volatile Future<?> future;

    private final ReentrantLock lock = new ReentrantLock();

    private final ScheduledThreadPoolExecutor executor;

    private LogWriteAgent(){
        executor = new ScheduledThreadPoolExecutor(1);
    }

    @Autowired
    public void setWorker(WriteWorker writeWorker){
        this.writeWorker = writeWorker;
    }

    /**
     *
     * start the ScheduleTask
     * We guarantee that the ScheduleTask's number <= 1
     * @return the future
     */
    @Override
    public Future<?> start(){

        if(isRunning()){
            return null;
        }

        lock.lock();
        try {

            if (isRunning()) {
                return null;
            }
            // get the Future that.It will be stopped in the worker thread.
            Future<?> future = this.executor.scheduleWithFixedDelay(
                    writeWorker,
                    1000,
                    1000,
                    TimeUnit.MILLISECONDS);

            writeWorker.setFuture(future);
            this.future = future;
        }finally {
            lock.unlock();
        }
        return future;
    }

    /**
     * return a state.
     * True:there is a scheduleTask is running.
     * False: this no scheduleTask is running.
     * @return the state
     */
    public boolean isRunning(){
        if(this.future == null){
            return false;
        }
        return !this.future.isCancelled();
    }

    public static LogWriteAgent getInstance(){
        return Loader.writeAgent;
    }

    private static class Loader {
        private static final LogWriteAgent writeAgent = new LogWriteAgent();

    }

}
