package com.echo.client.console.strategy;

import com.echo.client.cache.CacheFactory;
import com.echo.client.netty.EchoClient;
import io.netty.channel.ChannelFuture;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SendCommandHandler implements CommandHandler{

    private final EchoClient client = new EchoClient("localhost", 8550);

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
            client.start();
            ChannelFuture future = CacheFactory.getHandlerFuture().get("handler");

            future.channel().write("hello, this is strategy");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
