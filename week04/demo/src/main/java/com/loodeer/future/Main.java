package com.loodeer.future;

import java.util.concurrent.Callable;

/**
 * @author luzuheng
 * @since 2021-07-15 11:54
 */
public class Main {

    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor();
        Callable<Integer> subTask = new Callable<>() {
            @Override
            public Integer call() throws Exception {
                int millis = (int) (Math.random() * 1000);
                Thread.sleep(millis);
                return millis;
            }
        };
        MyFuture<Integer> future = executor.execute(subTask);
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
