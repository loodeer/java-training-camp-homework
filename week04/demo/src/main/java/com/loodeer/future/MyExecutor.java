package com.loodeer.future;

import java.util.concurrent.Callable;

/**
 * @author luzuheng
 * @since 2021-07-15 11:53
 */
public class MyExecutor {
    public <V> MyFuture<V> execute(final Callable<V> task) {
        final Object lock = new Object();
        final ExecuteThread<V> thread = new ExecuteThread<>(task, lock);
        thread.start();

        MyFuture<V> future = new MyFuture<>() {
            @Override
            public V get() throws Exception {
                synchronized (lock) {
                    while (!thread.isDone()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (thread.getException() != null) {
                        throw thread.getException();
                    }
                    return thread.getResult();
                }
            }
        };
        return future;
    }
}
