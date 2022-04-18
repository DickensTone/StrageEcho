package com.echo.client.echoCilent;


import com.echo.client.schedule.LogWriteAgent;
import com.echo.client.service.transportLog.WriteQueue;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Objects;

/**
 * Listing 2.3 ChannelHandler for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */

public class EchoClientHandler
        extends SimpleChannelInboundHandler<ByteBuf> {
    private final StringBuffer sb = new StringBuffer();
    private final PrintWriter pw = new PrintWriter(System.out);

    final String sendMessage;

    private final WriteQueue writeQueue;


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer(this.sendMessage,
                CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        pw.println(in.toString(CharsetUtil.UTF_8));
        pw.flush();
//
//        sb.setLength(0);
//        sb.append(in.toString(CharsetUtil.UTF_8));
//        // need to new an Object for avoiding overwrite the StringBuffer offered to queue.
//        writeQueue.addContent(new StringBuffer(sb));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
    }

    //Builder Mode
    public static class Builder{
        private final WriteQueue writeQueue;
        private final String sendMessage;

        public Builder(WriteQueue writeQueue, String sendMessage){
            this.writeQueue = writeQueue;
            this.sendMessage = sendMessage;
        }

        public EchoClientHandler build(){
            return new EchoClientHandler(this);
        }
    }

    private EchoClientHandler(Builder builder){
        this.writeQueue = builder.writeQueue;
        this.sendMessage = builder.sendMessage;
    }
}
