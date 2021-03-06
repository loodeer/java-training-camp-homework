package com.loodeer.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author luzuheng
 * @since 2021-07-07 00:10
 */
public class TimeClient {

  public static void main(String[] args) throws InterruptedException {
    int port = 8080;
    new TimeClient().connect(port, "127.0.0.1");
  }

  private void connect(int port, String host) throws InterruptedException {
    // 配置客户端 NIO 线程组
    NioEventLoopGroup group = new NioEventLoopGroup();

    try {
      Bootstrap b = new Bootstrap();
      b.group(group).channel(NioSocketChannel.class)
          .option(ChannelOption.TCP_NODELAY, true)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
              socketChannel.pipeline().addLast(new TimeClientHandler());
            }
          });

      ChannelFuture f = b.connect(host, port).sync();

      f.channel().closeFuture().sync();
    } finally {
      group.shutdownGracefully();
    }

  }
}
