package com.loodeer.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author luzuheng
 * @since 2021-07-06 14:32
 * 多路复用类，独立的线程
 * 负责轮询多路复用器 Selector，可以处理多个客户端的并发接入
 */
public class MultiplexerTimerServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器、绑定监听端口
     * @param port
     */
    public MultiplexerTimerServer(int port) {
        try {
            // 1. 打开 ServerSocketChannel，用于监听客户端的连接，他是所有客户端连接的父管道
            serverSocketChannel = ServerSocketChannel.open();
            // 2. 绑定监听端口，设置为非阻塞模式
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.configureBlocking(false);
            // 3. 创建 Reactor 线程，创建多路复用器并启动线程
            selector = Selector.open();
            // 4. 将 ServerSocketChannel 注册到 Reactor 线程的多路复用器 Selector 上，监听 ACCEPT 事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                // 5. 多路复用器在线程 run 方法的无限循环体内轮询准备就绪的 Key
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 多路复用器关闭后，所有注册在上面的 Channel 和 Pipe 等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) {
            return;
        }
        if (key.isAcceptable()) {
            // 6. 多路复用器监听到有新的客户端接入，处理新的接入请求，完成 TCP 三次握手，建立物理链路
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel sc = ssc.accept();
            // 7. 设置客户端链路为非阻塞模式
            sc.configureBlocking(false);
            // 8. 将新接入的客户端连接注册到 Reactor 线程的多路复用器上，监听读操作，读取客户端发送的网络请求
            sc.register(selector, SelectionKey.OP_READ);
        }
        if (key.isReadable()) {
            SocketChannel sc = (SocketChannel) key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            // 9. 异步读取客户端请求消息到缓冲区
            int readBytes = sc.read(readBuffer);
            if (readBytes > 0) {
                // 10. 数据处理，业务逻辑编排
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String body = new String(bytes, "UTF-8");
                System.out.println("The time server receive order : " + body);
                String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "Bad ORDER";
                doWrite(sc, currentTime);
            } else if (readBytes < 0) {
                key.cancel();
                sc.close();;
            } else {
                ; // 读到 0 字节，忽略
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            // 11. 业务数据处理完毕后，调用 SocketChannel 的异步 write 接口，将消息异步发送给客户端
            channel.write(writeBuffer);
        }
    }
}
