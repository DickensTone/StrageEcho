package com.echo.client.config;

import com.echo.client.schedule.WriteAgent;
import com.echo.client.service.transportLog.WriteQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingletonFactory {
    @Bean
    public WriteAgent getWriteAgent(){
        return WriteAgent.getInstance();
    }

    @Bean
    public WriteQueue getWriteQueue(){
        return WriteQueue.getInstance();
    }
}
