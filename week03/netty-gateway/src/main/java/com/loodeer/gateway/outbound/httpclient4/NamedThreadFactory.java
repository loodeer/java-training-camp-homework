package com.loodeer.gateway.outbound.httpclient4;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luzuheng
 * @since 2021-07-11 18:06
 */
public class NamedThreadFactory implements ThreadFactory {
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePrefix;
    private final boolean daemon;

    public NamedThreadFactory(String namePrefix) {
        this(namePrefix, false);
    }

    public NamedThreadFactory(String namePrefix, boolean daemon) {
        this.daemon = daemon;
        SecurityManager securityManager = System.getSecurityManager();
        group = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix;
    }

    @Override public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + "-thrad-" + threadNumber.getAndIncrement(), 0);
        t.setDaemon(daemon);
        return t;
    }
}
