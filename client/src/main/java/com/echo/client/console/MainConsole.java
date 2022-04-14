package com.echo.client.console;

import com.echo.client.console.strategy.CommandHandler;
import com.echo.client.console.strategy.CommandStrategyContext;

import java.io.*;

public class MainConsole implements Runnable{

    private InputStream input;

    public void setInput(InputStream in) {
        this.input = in;
    }

    /**
     * read command from console.
     * handle the command.
     */
    public void readCommand() throws IOException {
        try (BufferedReader sc = new BufferedReader(new InputStreamReader(new FilterInputStream(this.input){
            @Override
            public void close(){
                // avoiding closing System.in when closing the opened BufferReader
            }
        }))) {
            String commandLine = sc.readLine();
            String[] commands = commandLine.split(" ");

            if(commands.length != 3){
                System.out.println("Please input:echo [command] [address]");
                return;
            }
            if(!commands[0].equals("echo")){
                System.out.println("command not found  "+commands[0]);
                return;
            }

            CommandHandler strategy = CommandStrategyContext.getStrategy(commands[1]);
            strategy.handle(commands.length, commands);
        } catch (Exception e) {
            System.out.println("IOError:" + e.getMessage());
            throw e;
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                readCommand();
            }catch (Exception e){
                break;
            }
        }
    }
}
