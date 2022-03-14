package com.echo.client.service.lintener;

import com.echo.client.echoCilent.EchoClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClientBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, EchoClient> beansOfType = event.getApplicationContext().getBeansOfType(EchoClient.class);
        for (EchoClient echoClient : beansOfType.values()) {
            try {
                echoClient.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

