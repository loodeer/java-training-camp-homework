package com.loodeer;

import java.util.concurrent.CompletableFuture;

/**
 * @author luzuheng
 * @since 2021-07-18 17:24
 */
public class Main5 {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Integer result = CompletableFuture.supplyAsync(Main5::sum).join();

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
