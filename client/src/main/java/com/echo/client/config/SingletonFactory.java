package com.echo.client.config;

import com.echo.client.schedule.LogWriteAgent;
import com.echo.client.service.transportLog.WriteQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingletonFactory {

    @Bean
    public LogWriteAgent getWriteAgent(){
        return LogWriteAgent.getInstance();
    }

    @Bean
    public WriteQueue getWriteQueue(){
        return WriteQueue.getInstance();
    }
}
