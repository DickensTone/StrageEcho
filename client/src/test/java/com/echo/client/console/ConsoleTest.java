package com.echo.client.console;


import com.echo.client.console.strategy.CommandStrategyContext;
import com.echo.client.console.strategy.SendCommandHandler;

public class ConsoleTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MainConsole());
        CommandStrategyContext.register("send", new SendCommandHandler());
        t.setName("Console-input");
        t.start();
        t.join();
    }
}
