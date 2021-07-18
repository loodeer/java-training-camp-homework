package com.loodeer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author luzuheng
 * @since 2021-07-18 17:54
 */
public class Main4 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return sum();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        Future<Integer> future = fixedThreadPool.submit(new Main4());
        Integer result = future.get();

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private Integer sum() {
        return fibo(36);
    }

    private Integer fibo(int i) {
        if (i < 2) {
            return 1;
        }
        return fibo(i - 1) + fibo(i -2);
    }
}
