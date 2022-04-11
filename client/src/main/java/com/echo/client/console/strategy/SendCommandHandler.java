package com.echo.client.console.strategy;

import com.echo.client.echoCilent.EchoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SendCommandHandler implements CommandHandler{

    private EchoClient echoClient;

    public SendCommandHandler(){

    }

    @Autowired
    public void setEchoClient(EchoClient echoClient) {
        this.echoClient = echoClient;
    }

    @PostConstruct
    public void register(){
        CommandStrategyContext.register("send", this);
    }

    @Override
    public void handle(int argv, String[] args) {
        System.out.println(args[2]);
        try {
            echoClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
