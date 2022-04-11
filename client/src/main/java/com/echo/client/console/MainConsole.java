package com.echo.client.console;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.echo.client.console.strategy.CommandHandler;
import com.echo.client.console.strategy.CommandStrategyContext;

import java.io.*;

public class MainConsole implements Runnable{


    /**
     * read command from console.
     * handle the command.
     */
    public void readCommand() {
        try (BufferedReader sc = new BufferedReader(new InputStreamReader(new FilterInputStream(System.in){
            @Override
            public void close(){

            }
        }))) {
            String commandLine = sc.readLine();
            String[] commands = commandLine.split(" ");

            if(commands.length != 3){
                System.out.println("Please input:echo [command] [address]");
                return;
            }
            if(!commands[0].equals("echo")){
                System.out.println("can't reorganize "+commands[0]);
                return;
            }

            CommandHandler strategy = CommandStrategyContext.getStrategy(commands[1]);
            strategy.handle(commands[2]);
        } catch (Exception e) {
            System.out.println("IOError:" + e.getMessage());
        }

    }

    @Override
    public void run() {
        while (true) {
            readCommand();
        }
    }
}
