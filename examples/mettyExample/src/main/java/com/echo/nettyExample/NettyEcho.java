/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.echo.nettyExample;

import com.echo.nettyExample.echo.EchoClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * Server that accept the path of a file an echo back its content.
 */

public final class NettyEcho {
    private final int port;

    public NettyEcho(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        // Configure the server.
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        try {
            b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
                            System.out.println(byteBuf.toString(StandardCharsets.UTF_8));
                            byteBuf.retain();
                            Thread.sleep(1000);
                            ctx.write(byteBuf);
                        }
                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx,
                                                    Throwable cause) {
                            cause.printStackTrace();
                            ctx.close();
                        }
                        @Override
                        public void channelReadComplete(ChannelHandlerContext ctx) {
                            ctx.flush();
                        }

                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ByteBuf firstMessage = Unpooled.buffer(50);
                            firstMessage.writeBytes("hello".getBytes(StandardCharsets.UTF_8));
                            ctx.writeAndFlush(firstMessage);
                        }
                    });
                }
            }).remoteAddress("127.0.0.1", port);
            // Start the server.
            ChannelFuture f = b.connect().sync();
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        NettyEcho nettyEcho = new NettyEcho(7001);
        nettyEcho.start();
    }
}
