package com.loodeer;

/**
 * @author luzuheng
 * @since 2021-07-15 11:28
 */
public class Producer extends Thread {
    MyBlockingQueue<String> queue;

    public Producer(MyBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (true) {
                String task = String.valueOf(num);
                queue.put(task);
                System.out.println("produce task " + task);
                num ++;
                Thread.sleep((int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
