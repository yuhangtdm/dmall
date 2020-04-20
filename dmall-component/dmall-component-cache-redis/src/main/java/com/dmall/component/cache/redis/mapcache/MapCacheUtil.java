package com.dmall.component.cache.redis.mapcache;

import cn.hutool.core.util.StrUtil;
import com.dmall.component.cache.redis.DMallRedisProperties;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description: MapCacheUtil
 * @author: created by hang.yu on 2019/12/8 17:47
 */
@AllArgsConstructor
public class MapCacheUtil {

    private static final String MAP_CACHE_PREFIX = "mapCache_";

    private final RedisTemplate<String, Object> redisTemplate;

    private final DMallRedisProperties dMallRedisProperties;

    /**
     * 设置单个缓存
     */
    public void put(String key, String hashKey, Object result) {
        put(key, hashKey, result, Long.MAX_VALUE, null);
    }

    /**
     * 设置缓存并设置过期时间
     */
    public void put(String key, String hashKey, Object result, long timeout, TimeUnit timeUnit) {
        Assert.notNull(key, "key not null");
        Assert.notNull(hashKey, "key not null");
        redisTemplate.opsForHash().put(key, hashKey, result);
        // Long.MAX_VALUE 表示不设置过期时间
        if (timeout == Long.MAX_VALUE) {
            return;
        }
        // 大于0 则设置过期时间
        if (timeout > 0L) {
            redisTemplate.expire(key, timeout, timeUnit);
        } else {
            // 否则使用服务默认配置的过期时间
            redisTemplate.expire(key, dMallRedisProperties.getTtl(), dMallRedisProperties.getTtlUnitEnum());
        }
    }

    /**
     * 删除缓存
     */
    public void delete(String key, String... hashKey) {
        Assert.notNull(key, "key not null");
        Assert.notNull(hashKey, "hashKey not null");
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 根据key获取缓存
     */
    public List<Object> values(String key) {
        Assert.notNull(key, "key not null");
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 根据key和 hashKey获取缓存
     */
    public Object get(String key, String hashKey) {
        Assert.notNull(key, "key not null");
        Assert.notNull(hashKey, "hashKey not null");
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 批量设置缓存
     */
    public void batchPut(String key, Map<String, Object> caches) {
        caches.forEach((k, v) -> {
            put(key, k, v);
        });
    }

    /**
     * 获取key
     */
    public String getKey(String cacheName, Class<?> cacheService) {
        return getKey(cacheName, cacheService.getName());
    }

    /**
     * 获取key 保证唯一
     */
    public String getKey(String cacheName, String className) {
        return MAP_CACHE_PREFIX + cacheName + StrUtil.C_UNDERLINE + className;
    }

}
