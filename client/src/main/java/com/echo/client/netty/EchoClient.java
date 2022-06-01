package com.echo.client.netty;


import com.echo.client.cache.CacheFactory;
import com.echo.client.netty.initializer.EchoClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;

/**
 * Listing 2.4 Main class for the client
 *
 * @author dickenstone
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public EchoClient() {
        this.host = "localhost";
        this.port = 8023;
    }

    public void start()
            throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new EchoClientInitializer());
        ChannelFuture future = b.connect(host, port).sync();
        Map<String, ChannelFuture> map = CacheFactory.getHandlerFuture();
        map.put("handler", future);
    }

}