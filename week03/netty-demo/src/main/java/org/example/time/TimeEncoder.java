package org.example.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author luzuheng
 * @since 2021-07-12 14:55
 */
public class TimeEncoder extends ChannelOutboundHandlerAdapter {

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    UnitTime m = (UnitTime) msg;
    ByteBuf encoded = ctx.alloc().buffer(4);
    encoded.writeInt((int) m.value());
    ctx.write(encoded, promise);
  }
}
