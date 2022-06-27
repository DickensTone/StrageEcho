package com.echo.client.config;


import com.echo.client.console.MainConsole;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class MainThreadInit  implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        ExecutorService executor = new ThreadPoolExecutor(1, 1,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r->{
                    Thread t = new Thread(r,"MainConsole");
                    if(t.isDaemon()){
                        t.setDaemon(false);
                    }
                    return  t;
                });
        executor.execute(new MainConsole());
    }
}
