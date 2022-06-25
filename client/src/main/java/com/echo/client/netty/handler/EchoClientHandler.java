package com.echo.client.netty.handler;


import com.echo.client.service.transportLog.WriteQueue;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.io.PrintWriter;

/**
 * Listing 2.3 ChannelHandler for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */

public class EchoClientHandler
        extends SimpleChannelInboundHandler<ByteBuf> {
    private final StringBuffer sb = new StringBuffer();
    private final PrintWriter pw = new PrintWriter(System.out);

    private final WriteQueue writeQueue;

    private String command;


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, This is client",
                CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        pw.println(in.toString(CharsetUtil.UTF_8));
        pw.flush();
        ctx.writeAndFlush(Unpooled.copiedBuffer(command,
                CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
    }

    public static Builder getBuilder(WriteQueue writeQueue){
        return new Builder(writeQueue);
    }

    //Builder Mode
    public static class Builder{
        private final WriteQueue writeQueue;

        private String command;

        private Builder(WriteQueue writeQueue){
            this.writeQueue = writeQueue;
        }

        public EchoClientHandler build(){
            EchoClientHandler handler = new EchoClientHandler(this);
            handler.command = command;
            return handler;
        }

        public Builder setCommand(String command){
            this.command = command;
            return this;
        }
    }

    private EchoClientHandler(Builder builder){
        this.writeQueue = builder.writeQueue;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }
}
