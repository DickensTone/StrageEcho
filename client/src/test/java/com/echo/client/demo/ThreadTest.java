package com.echo.client.demo;

import org.junit.Test;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ThreadTest {

    public void test() throws InterruptedException {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("Thread start");
                Thread t = new Thread(r);
                t.setDaemon(false);
                return t;
            }
        });

        executorService.scheduleWithFixedDelay(new OutputStr(), 10, 1000, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        new ThreadTest().test();
    }

    @Test
    public void testOfficialDemo() throws InterruptedException {
        new BeeperControl().beepForAnHour();
        Thread.sleep(1000);

    }

    @Test
    public void testDaemonThrad(){
        new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        };
    }

    class OutputStr implements Runnable{
        private int times = 0;

        @Override
        public void run() {
            System.out.println(times++);
        }
    }


    class BeeperControl {
        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        public void beepForAnHour() {
            Runnable beeper = () -> System.out.println("beep");
            ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1, SECONDS);
            Runnable canceller = () -> beeperHandle.cancel(false);
            scheduler.schedule(canceller, 1, HOURS);
        }
    }
}
