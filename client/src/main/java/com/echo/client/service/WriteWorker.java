package com.echo.client.service;

import com.echo.client.domain.Transport;
import com.echo.client.repository.ServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class WriteWorker implements Runnable {
    @Autowired
    private WriteQueue writeQueue;

    @Autowired
    private ServiceLog serviceLog;

    private ReentrantLock reentrantLock = new ReentrantLock();

    private volatile boolean canceled = false;
    private volatile Future<?> future;


    public final boolean cancel() {

        canceled = true;
        Future<?> future = this.future;
        if (future != null) {
            return future.cancel(false);
        }
        return false;
    }


    public final void setFuture(Future<?> future) {
        this.future = future;
        if (canceled) {
            future.cancel(false);
        }
    }

    public final boolean isCanceled() {

        return canceled;
    }

    @Override
    public void run()   {
        if(canceled){
            return;
        }
        reentrantLock.lock();
        if(writeQueue.needDump()){
            StringBuffer sb = writeQueue.getContent();
            Transport transport = new Transport();
            transport.setContent(sb);
            transport.setCreateTime(Instant.now());
            transport.setModifyTime(Instant.now());

            serviceLog.save(transport);
        }else{
            cancel();
        }
        reentrantLock.unlock();
    }
}
