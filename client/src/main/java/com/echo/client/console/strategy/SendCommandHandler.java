package com.echo.client.console.strategy;

import com.echo.client.netty.EchoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class SendCommandHandler implements CommandHandler{

    private final EchoClient client = new EchoClient("localhost", 7001);

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
        try {
            client.setCommand(args[0]);
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
