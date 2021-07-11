package com.loodeer.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author luzuheng
 * @since 2021-07-11 17:17
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter {
    @Override public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        fullHttpRequest.headers().set("request-header", "loodeer");
    }
}
