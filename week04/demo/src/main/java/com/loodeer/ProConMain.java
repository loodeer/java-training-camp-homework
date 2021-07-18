package com.loodeer;

/**
 * @author luzuheng
 * @since 2021-07-15 11:32
 */
public class ProConMain {

    public static void main(String[] args) {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
        new Producer(queue).start();
        new Consumer(queue).start();
    }
}
