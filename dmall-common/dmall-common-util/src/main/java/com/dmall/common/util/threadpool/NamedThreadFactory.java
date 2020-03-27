package com.dmall.common.util.threadpool;

import cn.hutool.core.util.StrUtil;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 带有名称的线程池
 * @author: created by hang.yu on 2020/3/27 22:59
 */
public class NamedThreadFactory implements ThreadFactory {

    /**
     * 线程名前缀
     */
    private final String namePrefix;

    private final ThreadGroup group;

    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    private static final AtomicInteger threadNumber = new AtomicInteger(1);

    NamedThreadFactory(String poolName) {
        SecurityManager s = System.getSecurityManager();
        this.group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        if (StrUtil.isBlank(poolName)) {
            poolName = "pool";
        }
        this.namePrefix = StrUtil.format("{}-{}-thread-", poolName, poolNumber.getAndIncrement());
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement());
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
