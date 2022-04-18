package com.echo.client.service.transportLog;

import com.echo.client.domain.Transport;
import com.echo.client.repository.ServiceLog;
import com.echo.core.utils.SingletonUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.Future;

@Service
public class WriteWorker implements Runnable {

    private final ServiceLog serviceLog;

    public WriteWorker(ServiceLog serviceLog){
        this.serviceLog = serviceLog;
    }

    private volatile boolean canceled = false;
    private volatile Future<?> future;


    public final void cancel() {
        if (this.future != null) {
            future.cancel(false);
        }
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
        if(!SingletonUtil.linkedBlockingDeque.isEmpty()) {
            // make the queue as a blocking queue.
            StringBuffer sb = SingletonUtil.linkedBlockingDeque.poll();
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
