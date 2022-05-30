package com.echo.client.console.strategy;

import com.echo.client.netty.EchoClient;
import com.echo.client.netty.handler.impl.EchoClientHandler;
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

    /**
     * handle the send command.
     * Usually, it is used to send files to server.
     * @param argv command str nums.
     * @param args the specific commands.
     */
    @Override
    public void handle(int argv, String[] args) {
        client.setEchoClientHandler(EchoClientHandler.getBuilder(writeQueue).build());
        try {
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
