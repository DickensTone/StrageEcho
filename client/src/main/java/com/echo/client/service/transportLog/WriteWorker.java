package com.echo.client.service.transportLog;

import com.echo.client.domain.Transport;
import com.echo.client.repository.ServiceLog;
import com.echo.core.components.SingletonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WriteWorker {

    private final ServiceLog serviceLog;

    public WriteWorker(ServiceLog serviceLog) {
        this.serviceLog = serviceLog;
    }

    private volatile boolean isRunning = false;


    @Scheduled(initialDelay = 1, fixedDelay = 20, timeUnit = TimeUnit.MINUTES)
    public void run() {

        if (isRunning) {
            return;
        }

        isRunning = true;

        log.debug("dump the queue's data");
        int cnt = 0;
        try {
            while (!SingletonUtil.linkedBlockingDeque.isEmpty()) {
                // make the queue as a blocking queue.
                StringBuffer sb = SingletonUtil.linkedBlockingDeque.poll();
                if (sb != null) {
                    Transport transport = new Transport();
                    transport.setContent(sb);
                    transport.setCreateTime(Instant.now());
                    transport.setModifyTime(Instant.now());

                    serviceLog.save(transport);
                }
                cnt++;
            }
        } finally {
            isRunning = false;
        }
        log.debug("dump data finished, the total number = {}", cnt);
    }
}
