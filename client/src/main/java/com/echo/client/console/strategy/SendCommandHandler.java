package com.echo.client.console.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SendCommandHandler implements CommandHandler{

    @PostConstruct
    public void register(){
        CommandStrategyContext.register("send", this);
    }

    @Override
    public void handle(String command) {
        System.out.println(command);
    }
}
