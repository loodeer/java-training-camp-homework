package com.loodeer.nio;

/**
 * @author luzuheng
 * @since 2021-07-06 15:44
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        new Thread(new TimeClientHandler("127.0.0.1", port), "TimeClient-001").start();
    }
}
