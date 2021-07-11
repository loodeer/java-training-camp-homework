package com.loodeer.gateway.inbound;

import com.loodeer.gateway.filter.HeaderHttpRequestFilter;
import com.loodeer.gateway.filter.HttpRequestFilter;
import com.loodeer.gateway.outbound.httpclient4.HttpOutBoundHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.List;

/**
 * @author luzuheng
 * @since 2021-07-11 17:07
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private final List<String> proxyServer;
    private final HttpOutBoundHandler handler;
    private HttpRequestFilter filter = new HeaderHttpRequestFilter();

    public HttpInboundHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
        this.handler = new HttpOutBoundHandler(this.proxyServer);
    }

    @Override public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest httpRequest = (FullHttpRequest) msg;
            handler.handle(httpRequest, ctx, filter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
