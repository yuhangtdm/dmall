package com.dmall.component.cache.redis.mapcache;

import cn.hutool.core.util.StrUtil;
import com.dmall.component.cache.redis.properties.DMallRedisProperties;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/8 17:47
 */
@AllArgsConstructor
public class MapCacheUtil {

    private RedisTemplate dmallRedisTemplate;

    private DMallRedisProperties dMallRedisProperties;

    private Environment environment;

    /**
     * 批量设置缓存
     */
    public void batchPut(String key, Map<String, Object> cache) {
        cache.forEach((k,v) ->{
            put(key,k,v);
        });
    }

    /**
     * 设置单个缓存
     */
    public void put(String key, String hashKey, Object result) {
        put(key, hashKey, result, 0L, null);
    }

    /**
     * 设置缓存并设置过期时间
     */
    public void put(String key, String hashKey, Object result, long timeout, TimeUnit timeUnit) {
        dmallRedisTemplate.opsForHash().put(key, hashKey, result);
        if (timeout > 0L) {
            dmallRedisTemplate.expire(key, timeout, timeUnit);
        } else {
            dmallRedisTemplate.expire(key, dMallRedisProperties.getTtl(), dMallRedisProperties.getTtlUnitEnum());
        }
    }

    /**
     * 删除缓存
     */
    public void delete(String key, String... hashKey) {
        dmallRedisTemplate.opsForHash().delete(key, hashKey);

    }

    /**
     * 获取key
     */
    public String getkey(String cacheName, Class<?> cacheService) {
        return getkey(cacheName, cacheService.getName());
    }

    public String getkey(String cacheName, String className) {
        return new StringBuilder(dMallRedisProperties.getCacheKeyPrefix())
                .append(StrUtil.UNDERLINE)
                .append(environment.getActiveProfiles().length == 1 ? environment.getActiveProfiles()[0] : "local")
                .append(StrUtil.UNDERLINE)
                .append(cacheName)
                .append(StrUtil.COLON)
                .append(className)
                .toString();
    }
}
