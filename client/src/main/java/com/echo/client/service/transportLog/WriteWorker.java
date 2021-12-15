package com.echo.client.service.transportLog;

import com.echo.client.domain.Transport;
import com.echo.client.repository.ServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        if (this.future != null) {
            canceled = future.cancel(false);
            return canceled;
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
            Transport transport = new Transport();
            transport.setContent(writeQueue.getContent());
            transport.setCreateTime(Instant.now());
            transport.setModifyTime(Instant.now());

            serviceLog.save(transport);
        }else{
            cancel();
        }
        reentrantLock.unlock();
    }
}
