package com.loodeer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @author luzuheng
 * @since 2021-06-29 10:04
 */
public class NIOSocket {
    private static LinkedList<SocketChannel> clients = new LinkedList<>();

    private static void startClientChannelHandleThread() {
        new Thread(() -> {
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);

            // 处理客户端连接
            for (SocketChannel c : clients) {
                // 非阻塞，>0 表示读取到的字节数量，0或-1表述未读取到或读取异常
                int num = 0;
                try {
                    num = c.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (num > 0) {
                    buffer.flip();
                    byte[] clientBytes = new byte[buffer.limit()];
                    // 从缓冲区，读取到内存中
                    buffer.get(clientBytes);

                    System.out.println(c.socket().getPort() + ":" + new String(clientBytes));

                    // 清空缓冲区
                    buffer.clear();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(9090));
        // 设置阻塞接受客户端连接
        socketChannel.configureBlocking(true);

        // 开始 client 处理线程
        startClientChannelHandleThread();

        while (true) {
            // 接受客户端连接；非阻塞，无客户端返回 null
            SocketChannel client = socketChannel.accept();

            if (client == null) {
                System.out.println("no client");
            } else {
                // 设置读非阻塞
                client.configureBlocking(false);

                int port = client.socket().getPort();
                System.out.println("client port:" + port);

                clients.add(client);
            }
        }
    }
}
