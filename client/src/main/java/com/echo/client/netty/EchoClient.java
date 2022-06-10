package com.echo.client.netty;


import com.echo.client.netty.handler.EchoClientHandler;
import com.echo.client.service.transportLog.WriteQueue;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Listing 2.4 Main class for the client
 *
 * @author dickenstone
 */
public class EchoClient {
    private final String host;
    private final int port;
    private String command;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public EchoClient() {
        this.host = "localhost";
        this.port = 8055;
    }

    public void start()
            throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(EchoClientHandler.getBuilder(WriteQueue.getInstance())
                                    .setCommand(command).build());
                        }
                    });
            ChannelFuture future = b.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public void setCommand(String command) {
        this.command = command;
    }
}

