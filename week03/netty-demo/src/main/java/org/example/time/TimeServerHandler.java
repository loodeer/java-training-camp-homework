package org.example.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author luzuheng
 * @since 2021-07-12 11:28
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    ChannelFuture f = ctx.writeAndFlush(new UnitTime());
    f.addListener(ChannelFutureListener.CLOSE);

//    ByteBuf time = ctx.alloc().buffer(4);
//    time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
//
//    ChannelFuture f = ctx.writeAndFlush(time);
//    f.addListener(new ChannelFutureListener() {
//      @Override
//      public void operationComplete(ChannelFuture channelFuture) throws Exception {
//        assert f == channelFuture;
//        ctx.close();
//      }
//    });
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
