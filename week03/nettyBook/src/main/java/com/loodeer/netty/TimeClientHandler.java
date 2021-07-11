package com.loodeer.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author luzuheng
 * @since 2021-07-07 00:15
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf body = (ByteBuf) msg;
        System.out.println("Now is : " + body + " ; the counter is : " + ++counter);
        ctx.close();
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
