package com.echo.client.console.strategy;

import com.echo.client.echoCilent.EchoClient;
import com.echo.client.echoCilent.EchoClientHandler;
import com.echo.client.service.transportLog.WriteQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SendCommandHandler implements CommandHandler{

    private final EchoClient client = new EchoClient("localhost", 8550);


    @Autowired
    public void setWriteQueue(WriteQueue writeQueue) {
        this.writeQueue = writeQueue;
    }



    private WriteQueue writeQueue;

    public SendCommandHandler(){

    }



    @PostConstruct
    public void register(){
        CommandStrategyContext.register("send", this);
    }

    @Override
    public void handle(int argv, String[] args) {
        client.setEchoClientHandler(new EchoClientHandler.Builder(writeQueue, args[argv-1]).build());
        try {
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
