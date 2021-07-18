package com.loodeer.future;

/**
 * @author luzuheng
 * @since 2021-07-15 11:52
 */
public interface MyFuture<V> {
    V get() throws Exception;
}
