package com.loodeer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luzuheng
 * @since 2021-07-18 17:24
 */
public class Main1 {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        AtomicInteger value = new AtomicInteger();
        Thread t1 = new Thread(() -> {
            value.set(sum());
        });
        t1.start();
        t1.join();

        int result = value.get();

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int i) {
        if (i < 2) {
            return 1;
        }
        return fibo(i - 1) + fibo(i - 2);
    }

}
