package com.echo.server.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

public class EchoEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, ByteBuf out)  {
        msg.writeBytes("@@".getBytes(StandardCharsets.UTF_8));
        out.writeBytes(msg);
    }
}
