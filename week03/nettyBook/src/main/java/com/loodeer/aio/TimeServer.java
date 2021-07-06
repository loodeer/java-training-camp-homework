package com.loodeer.aio;

/**
 * @author luzuheng
 * @since 2021-07-06 17:28
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8081;
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}
