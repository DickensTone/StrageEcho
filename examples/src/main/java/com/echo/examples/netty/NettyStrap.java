package com.echo.examples.netty;

import com.echo.client.netty.EchoClient;
import com.echo.server.fileserver.FileServer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class NettyStrap {
    public static ExecutorService executor = Executors.newFixedThreadPool(1);
    public static void main(String[] args) throws Exception {
        CountDownLatch downLatch = new CountDownLatch(1);
        executor.submit(()->{
            try {
                downLatch.await();
                FileServer fileServer = new FileServer();
                fileServer.setPort(8023);
                fileServer.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

        downLatch.countDown();
        Thread.sleep(1000);
        EchoClient echoClient = new EchoClient();
        echoClient.start();
    }

}
