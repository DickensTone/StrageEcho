package com.echo.client.cache;


import io.netty.channel.ChannelFuture;

import java.util.HashMap;
import java.util.Map;

public class CacheFactory {
    private static Map<String, ChannelFuture> handlerFuture = new HashMap<>();

    public static Map<String, ChannelFuture> getHandlerFuture(){
        return handlerFuture;
    }
}
