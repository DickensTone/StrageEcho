package com.echo.client.netty.initializer;

import com.echo.client.netty.encoder.EchoEncoder;
import com.echo.client.netty.handler.impl.EchoClientHandler;
import com.echo.client.service.transportLog.WriteQueue;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;


public class  EchoClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,
                Unpooled.wrappedBuffer("@@".getBytes())));
        ch.pipeline().addLast(new EchoEncoder());
        ch.pipeline().addLast(EchoClientHandler.getBuilder(WriteQueue.getInstance()).build());
    }

}
