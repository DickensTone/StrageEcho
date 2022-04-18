package com.echo.client.config;

import com.echo.client.console.MainConsole;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadFactory;

@Component
public class MainThreadInit {

    @PostConstruct
    public void init(){
        MainConsole console = new MainConsole();
        console.setInput(System.in);
        Thread t = new Thread(console);
        t.setDaemon(false);
        t.setName("Main-Console");
        t.start();
    }
}
