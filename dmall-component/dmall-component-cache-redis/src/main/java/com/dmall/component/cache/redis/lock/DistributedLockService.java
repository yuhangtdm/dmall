package com.dmall.component.cache.redis.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.time.Duration;
import java.util.Collections;

/**
 * @description: 分布式锁服务
 * @author: created by hang.yu on 2019/11/3 23:07
 */

public class DistributedLockService {

    private final DefaultRedisScript<Boolean> redisScript;

    private final RedisTemplate redisTemplate;

    public DistributedLockService(RedisTemplate redisTemplate, DefaultRedisScript<Boolean> redisScript) {
        this.redisTemplate = redisTemplate;
        this.redisScript = redisScript;
    }

    /**
     * 加锁
     *
     * @param key 锁的key
     * @param value 锁的值
     * @param timeout 锁的过期时间
     */
    public Boolean getLock(String key, String value, long timeout) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(timeout));
    }

    /**
     * 解锁
     *
     * @param key 锁的key
     * @param requestId 锁的值
     */
    public Boolean releaseLock(String key, String requestId) {
        return (Boolean)redisTemplate.execute(redisScript, Collections.singletonList(key), requestId);
    }

}
