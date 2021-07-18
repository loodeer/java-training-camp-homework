package com.loodeer;

import java.util.concurrent.Semaphore;

/**
 * @author luzuheng
 * @since 2021-07-18 17:24
 */
public class Main3 {

    private volatile Integer value;
    private Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Main3 main3 = new Main3();
        Thread t1 = new Thread(() -> {
            try {
                main3.sum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread.sleep(10);

        int result = main3.getValue();

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    synchronized private void sum() throws InterruptedException {
        semaphore.acquire();
        value = fibo(36);
        semaphore.release();
    }

    synchronized private int getValue() throws InterruptedException {
        semaphore.acquire();
        int result = value;
        semaphore.release();
        return result;
    }

    private static int fibo(int i) {
        if (i < 2) {
            return 1;
        }
        return fibo(i - 1) + fibo(i - 2);
    }

}
