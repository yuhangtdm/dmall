package com.dmall.component.cache.redis.lock;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import java.time.Duration;
import java.util.Collections;

/**
 * @description:
 * @author: created by yuhang on 2019/11/3 23:07
 */

public class DistributedLock {

    private StringRedisTemplate stringRedisTemplate;

    DefaultRedisScript<Boolean> redisScript;

    public DistributedLock(StringRedisTemplate stringRedisTemplate, DefaultRedisScript<Boolean> redisScript) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisScript = redisScript;
    }

    /**
     * 加锁
     *
     * @param key     锁的key
     * @param value   锁的值
     * @param timeout 锁的过期时间
     */
    public boolean getLock(String key, String value, long timeout) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(timeout));
    }

    /**
     * 解锁
     *
     * @param key       锁的key
     * @param requestId 锁的值
     */
    public boolean releaseLock(String key, String requestId) {
        return stringRedisTemplate.execute(redisScript, Collections.singletonList(key), requestId);

    }
}
