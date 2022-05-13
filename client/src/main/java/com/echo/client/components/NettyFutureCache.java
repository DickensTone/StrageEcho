package com.echo.client.components;

import io.netty.channel.ChannelFuture;

import java.util.HashMap;
import java.util.Map;

public class NettyFutureCache {
    public static final Map<String, ChannelFuture> futureMap = new HashMap<>();
}
