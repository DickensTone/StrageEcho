package com.echo.client.service.transportLog;

import com.echo.client.domain.Transport;
import com.echo.client.repository.ServiceLog;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class WriteWorker implements Runnable {

    private final WriteQueue writeQueue;

    private final ServiceLog serviceLog;

    public WriteWorker(WriteQueue writeQueue, ServiceLog serviceLog){
        this.writeQueue = writeQueue;
        this.serviceLog = serviceLog;
    }

    private final ReentrantLock reentrantLock = new ReentrantLock();

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
        this.canceled = false;
    }

    public final boolean isCanceled() {

        return canceled;
    }

    @Override
    public void run()   {
        if(writeQueue.needDump()) {
            // make the queue as a blocking queue.
            StringBuffer sb = writeQueue.getContent();
            if (sb != null) {
                Transport transport = new Transport();
                transport.setContent(sb);
                transport.setCreateTime(Instant.now());
                transport.setModifyTime(Instant.now());

                serviceLog.save(transport);
            }
        }else {
            cancel();
        }
    }
}
