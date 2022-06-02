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

}
