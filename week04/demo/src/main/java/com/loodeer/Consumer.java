package com.loodeer;

/**
 * @author luzuheng
 * @since 2021-07-15 11:30
 */
public class Consumer extends Thread {
    MyBlockingQueue<String> queue;

    public Consumer(MyBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String task = queue.take();
                System.out.println("handle task " + task);
                Thread.sleep((int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
