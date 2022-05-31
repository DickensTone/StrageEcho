package com.echo.client.console;


import com.echo.client.console.strategy.CommandHandler;
import com.echo.client.console.strategy.CommandStrategyContext;
import com.echo.client.console.strategy.SendCommandHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


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
        String s = "ppgod";
        String strategyName = "send";
        CommandStrategyContext.register(strategyName, (argv, args) -> args[0] = s);
        String[] inputString = new String[]{"ppppppppppppppp"};

        CommandHandler handler = CommandStrategyContext.getStrategy(strategyName);
        handler.handle(1, inputString);
        Assertions.assertEquals(inputString[0], s);
    }

    @Test
    public void test() throws InterruptedException {
        String address = "sssssss";
        InputStream inputStream = new ByteArrayInputStream(("echo send "+address).getBytes(StandardCharsets.UTF_8));
        MainConsole console = new MainConsole();
        console.setInput(inputStream);
        Thread t = new Thread(console);
        final String[] p = new String[1];
        CommandStrategyContext.register("send", (argv, args) -> p[0] = args[2]);
        t.setName("Console-input");
        t.start();
        t.join();
        Assertions.assertEquals(p[0], address);
    }
}
