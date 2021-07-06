package com.loodeer.nio;

/**
 * @author luzuheng
 * @since 2021-07-06 14:32
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        MultiplexerTimerServer timerServer = new MultiplexerTimerServer(port);
        new Thread(timerServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
