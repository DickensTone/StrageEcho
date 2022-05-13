package com.echo.client.netty;


import com.echo.client.netty.handler.EchoClientHandler;
import com.echo.client.netty.encoder.EchoEncoder;
import com.echo.client.netty.initializer.EchoClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * Listing 2.4 Main class for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class EchoClient {
    private final String host;
    private final int port;

    private EchoClientHandler echoClientHandler;

    public void setEchoClientHandler(EchoClientHandler echoClientHandler){
        this.echoClientHandler = echoClientHandler;
    }

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public EchoClient(){
        this.host = "localhost";
        this.port = 8023;
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
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,
                                    Unpooled.wrappedBuffer("@@".getBytes())));
                            ch.pipeline().addLast(new EchoEncoder());
                            ch.pipeline().addLast(new EchoClientInitializer());
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}