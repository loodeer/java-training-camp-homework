package com.loodeer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author luzuheng
 * @since 2021-07-18 22:31
 */
public class Main6 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        FutureTask<Integer> futureTask = new FutureTask<>(Main6::sum);

        new Thread(futureTask).start();

        Integer result = futureTask.get();

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
