package com.loodeer.gateway.outbound.httpclient4;

import com.loodeer.gateway.filter.HeaderHttpRequestFilter;
import com.loodeer.gateway.filter.HeaderHttpResponseFilter;
import com.loodeer.gateway.filter.HttpRequestFilter;
import com.loodeer.gateway.filter.HttpResponseFilter;
import com.loodeer.gateway.router.HttpEndpointRouter;
import com.loodeer.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author luzuheng
 * @since 2021-07-11 17:09
 */
public class HttpOutBoundHandler {

    private CloseableHttpAsyncClient httpclient;
    private ExecutorService proxyService;
    private List<String> backendUrls;

    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public HttpOutBoundHandler(List<String> backends) {
        this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        ThreadPoolExecutor.CallerRunsPolicy handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores, keepAliveTime, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize), new NamedThreadFactory("proxyService"), handler);

        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpclient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setKeepAliveStrategy(((httpResponse, httpContext) -> 6000))
                .build();

        httpclient.start();
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/") ? backend.substring(0, backend.length() - 1) : backend;
    }

    public void handle(FullHttpRequest httpRequest, ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + httpRequest.uri();
        filter.filter(httpRequest, ctx);
        proxyService.submit(() -> fetchGet(httpRequest, ctx, url));
    }

    private void fetchGet(FullHttpRequest httpRequest, ChannelHandlerContext ctx, String url) {
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpGet.setHeader("response-header", httpRequest.headers().get("request-header"));

        httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override public void completed(HttpResponse httpResponse) {
                handleResponse(httpRequest, ctx, httpResponse);
            }

            @Override public void failed(Exception e) {
                httpGet.abort();
                e.printStackTrace();
            }

            @Override public void cancelled() {
                httpGet.abort();
            }
        });
    }

    private void handleResponse(FullHttpRequest httpRequest, ChannelHandlerContext ctx, HttpResponse httpResponse) {
        FullHttpResponse response = null;

        try {
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
             response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));

             response.headers().set("Content-Type", "application/json");
             response.headers().setInt("Content-Length", Integer.parseInt(httpResponse.getFirstHeader("Content-Length").getValue()));
             filter.filter(response);
        } catch (IOException e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
            ctx.close();
        } finally {
            if (httpRequest != null) {
                if (!HttpUtil.isKeepAlive(httpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }
}
