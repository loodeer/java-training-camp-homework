package com.loodeer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author luzuheng
 * @since 2021-06-29 10:15
 */
public class MultiplexingSocket {

    static ByteBuffer buffer = ByteBuffer.allocateDirect(4096);

    public static void main(String[] args) throws IOException {
        LinkedList<SocketChannel> clients = new LinkedList<>();

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(9090));
        // 设置非阻塞，接受客户端
        socketChannel.configureBlocking(false);

        // 多路复用器（JDK 包装的代理,select/poll/epoll/kqueue）
        Selector selector = Selector.open(); // Java 自动代理，默认为 epoll
//        Selector selector = PollSelectorProvider.provider().openSelector();//指定为poll

        // 将服务器 socket 注册到 多路复用器
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 2. 轮询多路复用器
        // 先询问有没有连接，如果有则返回数量以及对应的对象(fd)
        while (selector.select() > 0) {
            System.out.println();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeys.iterator();

            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();

                // 2.1 处理新的连接
                if (key.isAcceptable()) {
                    SocketChannel client = socketChannel.accept();
                    client.configureBlocking(false);

                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("new client : " + client.getRemoteAddress());
                } else if (key.isReadable()) {
                    // 2.2 处理读取数据
                    readDataFromSocket(key);
                }
            }
        }
    }

    protected static void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        int num = socketChannel.read(buffer);

        if (num > 0) {
            buffer.flip();
            byte[] clientBytes = new byte[buffer.limit()];
            // 从缓冲区 读到内存
            buffer.get(clientBytes);

            System.out.println(socketChannel.socket().getPort() + ":" + new String(clientBytes));

            buffer.clear();
        }
    }
}
