package com.loodeer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luzuheng
 * @since 2021-07-18 17:24
 */
public class Main2 {

    private volatile Integer value;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Main2 main2 = new Main2();
        Thread t1 = new Thread(main2::sum);
        t1.start();

        int result = main2.getValue();

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    synchronized private void sum() {
        value = fibo(36);
        notifyAll();
    }

    synchronized private int getValue() throws InterruptedException {
        while (value == null) {
            wait();
        }
        return value;
    }

    private static int fibo(int i) {
        if (i < 2) {
            return 1;
        }
        return fibo(i - 1) + fibo(i - 2);
    }

}
