package com.loodeer.gateway.inbound;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

/**
 * @author luzuheng
 * @since 2021-07-11 17:02
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {
    private final List<String> proxyServer;

    public HttpInboundInitializer(List<String> proxyServers) {
        this.proxyServer = proxyServers;
    }

    @Override protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new HttpInboundHandler(this.proxyServer));
    }
}
