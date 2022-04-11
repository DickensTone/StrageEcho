package com.echo.client.console;


import com.echo.client.console.strategy.CommandHandler;
import com.echo.client.console.strategy.CommandStrategyContext;
import com.echo.client.console.strategy.SendCommandHandler;
import com.echo.client.echoCilent.EchoClient;
import org.junit.Assert;
import org.junit.Test;

public class ConsoleTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MainConsole());
        CommandStrategyContext.register("send", new SendCommandHandler());
        t.setName("Console-input");
        t.start();
        t.join();
    }


    @Test
    public void testStrategyRegister(){
        final String[] p = new String[1];
        String s = "ppgod";
        String strategyName = "send";
        CommandStrategyContext.register(strategyName, (argv, args) -> p[0]=args[0]);

        CommandHandler handler = CommandStrategyContext.getStrategy(strategyName);
        handler.handle(1, new String[]{s});
        Assert.assertEquals(p[0], s);
    }
}
