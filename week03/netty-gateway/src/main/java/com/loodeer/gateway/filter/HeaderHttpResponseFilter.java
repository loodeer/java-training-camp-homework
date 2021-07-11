package com.loodeer.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author luzuheng
 * @since 2021-07-11 17:25
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override public void filter(FullHttpResponse response) {
        response.headers().set("response-header", "loodeer");
    }
}
