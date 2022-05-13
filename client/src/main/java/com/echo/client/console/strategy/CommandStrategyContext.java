package com.echo.client.console.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandStrategyContext {

    private static final Map<String, CommandHandler> strategyMap = new HashMap<>();

    /**
     *  get a Strategy to handle command from cache;
     * @param command command name
     * @return command handling's Strategy
     */
    public static CommandHandler getStrategy(String command) {
        return strategyMap.get(command);
    }

    /**
     * register the handler to StrategyMap.
     * @param name strategy's name
     * @param handler strategy's instance
     */
    public static void register(String name, CommandHandler handler){
        strategyMap.put(name, handler);
    }

}
