package com.echo.client.echoCilent;


import com.echo.client.schedule.WriteAgent;
import com.echo.client.service.transportLog.WriteQueue;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Listing 2.3 ChannelHandler for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
@Service
public class EchoClientHandler
        extends SimpleChannelInboundHandler<ByteBuf> {
    private final StringBuffer sb = new StringBuffer();

    @Autowired
    private WriteQueue writeQueue;

    public EchoClientHandler(WriteQueue writeQueue, WriteAgent writeAgent) {
        Objects.requireNonNull(writeQueue);
        Objects.requireNonNull(writeAgent);
        writeQueue.registerListener(writeAgent);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("C:\\Users\\ddt\\Desktop\\helloIO.txt\n",
                CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        sb.setLength(0);
        sb.append(in.toString(CharsetUtil.UTF_8));
        // need to new a Object for avoiding overwrite the StringBuffer offered to queue.
        writeQueue.addContent(new StringBuffer(sb));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();

    }
}
