package com.loodeer.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author luzuheng
 * @since 2021-07-06 17:14
 */
public class AsyncTimeServerHandler implements Runnable {

    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncTimeServerHandler(int port) {
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        /*
         * 在完成一组正在执行的操作之前，允许当前的线程一直阻塞。
         * 线程在这里阻塞，防止服务端执行完成退出。
         * 在实际项目中，不需要启动独立的线程来处理 AsynchronousServerSocketChannel
         */
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接受客户端的连接
     * 由于是异步操作，传递一个 CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> 类型的 handler 实例接受 accept 操作成功的通知消息。
     */
    private void doAccept() {
        asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}
