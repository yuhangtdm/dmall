package com.dmall.common.util.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 线程池工具类
 * @author: created by hang.yu on 2020/3/27 22:46
 */
public class ThreadPoolUtil {

    private ThreadPoolUtil() {
    }

    private static final Integer CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 20;

    private static final Integer KEEP_ALIVE_TIME = 60;

    private static final Integer QUEUE_SIZE = 200;

    private static final AtomicInteger threadPoolNumber = new AtomicInteger(1);

    /**
     * 应用中线程池最大的数量
     */
    private static final Integer MAX_POOL_SIZE = 20;


    public static ThreadPoolExecutor createThreadPool(String poolName) {
        return createThreadPool(poolName, CORE_POOL_SIZE, CORE_POOL_SIZE * 2, KEEP_ALIVE_TIME, QUEUE_SIZE);
    }

    /**
     * 创建线程池
     *
     * @param poolName      线程池名称
     * @param corpPoolSize  核心线程池大小
     * @param maxPoolSize   最大线程池大小
     * @param keepAliveTime 空闲线程存活时间
     * @param queueSize     队列长度
     */
    public static ThreadPoolExecutor createThreadPool(String poolName, Integer corpPoolSize,
                                                      Integer maxPoolSize, Integer keepAliveTime, Integer queueSize) {
        return new ThreadPoolExecutor(corpPoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueSize), new NamedThreadFactory(poolName));
    }

}
