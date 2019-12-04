package com.dmall.component.cache.redis.mapcache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.component.cache.redis.exception.CacheRedisErrorEnum;
import com.dmall.component.cache.redis.exception.CacheRedisException;
import com.dmall.component.cache.redis.properties.DMallRedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: mapCache切面
 * @author: created by hang.yu on 2019/11/23 22:35
 */
@Slf4j
@Aspect
public class MapCacheAspect {

    private RedisTemplate dmallRedisTemplate;

    private DMallRedisProperties dMallRedisProperties;

    private Environment environment;

    public MapCacheAspect(RedisTemplate dmallRedisTemplate, DMallRedisProperties dMallRedisProperties, Environment environment) {
        log.info("MapCacheAspect init success");
        this.dmallRedisTemplate = dmallRedisTemplate;
        this.dMallRedisProperties = dMallRedisProperties;
        this.environment = environment;
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapCacheable)")
    public void mapCacheable() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapGetCache)")
    public void mapGetCache() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapDeleteCache)")
    public void mapDeleteCache() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapPutCache)")
    public void mapPutCache() {
    }

    /**
     * map缓存切面方法 用于获取list的方法
     */
    @Around("mapCacheable()")
    public Object mapCacheable(ProceedingJoinPoint joinPoint) {
        String methodName = "";
        try {
            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String className = joinPoint.getTarget().getClass().getName();
            MapCacheable mapCacheable = methodSignature.getMethod().getAnnotation(MapCacheable.class);
            String key = StrUtil.isNotBlank(mapCacheable.key()) ? mapCacheable.key()
                    : getkey(mapCacheable.cacheNames(), className);
            List values = dmallRedisTemplate.opsForHash().values(key);
            if (CollUtil.isNotEmpty(values)) {
                log.info("cache hit,key:{}", key);
                return values;
            } else {
                log.info("cache miss,key:{}", key);
                Object result = joinPoint.proceed(args);
                List list = (List) result;
                for (Object o : list) {
                    Long id = (Long) ReflectUtil.getFieldValue(o, Constants.ID);
                    put(key, String.valueOf(id), o, mapCacheable.timeout(), mapCacheable.timeUnit());
                }
                return result;
            }

        } catch (Throwable e) {
            log.error("MapCacheable method:{} catch error", methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_CACHE_ABLE_ERROR);
        }
    }

    /**
     * map缓存切面方法 用于获取对象的方法
     * 入参为id
     */
    @Around("mapGetCache()")
    public Object mapGetCache(ProceedingJoinPoint joinPoint) {
        String methodName = "";
        try {

            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapGetCache mapGetCache = methodSignature.getMethod().getAnnotation(MapGetCache.class);
            String className = joinPoint.getTarget().getClass().getName();
            String key = StrUtil.isNotBlank(mapGetCache.key()) ? mapGetCache.key()
                    : getkey(mapGetCache.cacheNames(), className);
            Object cacheResult = dmallRedisTemplate.opsForHash().get(key, String.valueOf(args[0]));
            if (cacheResult != null) {
                log.info("cache hit,key:{},hashKey:{}", key, args[0]);
                return cacheResult;
            } else {
                log.info("cache miss,key:{},hashKey:{}", key, args[0]);
                Object result = joinPoint.proceed(args);
                put(key, String.valueOf(args[0]), result, mapGetCache.timeout(), mapGetCache.timeUnit());
                return result;
            }

        } catch (Throwable e) {
            log.error("MapGetCache method:{} catch error", methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_GET_CACHE_ERROR);
        }
    }

    /**
     * map缓存切面方法 用于新增或更新对象的方法
     */
    @Around("mapPutCache()")
    public Object mapPutCache(ProceedingJoinPoint joinPoint) {
        String methodName = "";
        try {

            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapPutCache mapPutCache = methodSignature.getMethod().getAnnotation(MapPutCache.class);
            String className = joinPoint.getTarget().getClass().getName();
            String key = StrUtil.isBlank(mapPutCache.key()) ? getkey(mapPutCache.cacheNames(), className)
                    : mapPutCache.key();
            Object result = joinPoint.proceed(args);
            Object fieldValue = ReflectUtil.getFieldValue(args[0], Constants.ID);
            put(key, String.valueOf(fieldValue), args[0], mapPutCache.timeout(), mapPutCache.timeUnit());
            return result;
        } catch (Throwable e) {
            log.error("MapPutCache method:{} catch error", methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_PUT_CACHE_ERROR);
        }
    }

    /**
     * map切面缓存方法 用于删除缓存的方法
     */
    @Around("mapDeleteCache()")
    public Object mapDeleteCache(ProceedingJoinPoint joinPoint) {
        String methodName = "";
        try {

            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapDeleteCache mapDeleteCache = methodSignature.getMethod().getAnnotation(MapDeleteCache.class);
            String className = joinPoint.getTarget().getClass().getName();
            String key = StrUtil.isBlank(mapDeleteCache.key()) ? getkey(mapDeleteCache.cacheNames(), className)
                    : mapDeleteCache.key();
            Object result = joinPoint.proceed(args);
            dmallRedisTemplate.opsForHash().delete(key, String.valueOf(args[0]));
            log.info("delete cache success,key:{},hashKey:{}", key, args[0]);
            return result;
        } catch (Throwable e) {
            log.warn("MapPutCache method:{} catch error", methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_DELETE_CACHE_ERROR);
        }
    }

    /**
     * 获取key
     */
    private String getkey(String cacheName, String className){
        return new StringBuilder(dMallRedisProperties.getCacheKeyPrefix())
                .append(StrUtil.UNDERLINE)
                .append(environment.getActiveProfiles()[0])
                .append(StrUtil.UNDERLINE)
                .append(cacheName)
                .append(StrUtil.COLON)
                .append(className)
                .toString();
    }

    /**
     * 设置缓存并设置过期时间
     */
    public void put(String key, String hashKey, Object result, long timeout, TimeUnit timeUnit){
        dmallRedisTemplate.opsForHash().put(key, hashKey, result);
        if (timeout > 0L ) {
            dmallRedisTemplate.expire(key,timeout, timeUnit);
        } else {
            dmallRedisTemplate.expire(key, dMallRedisProperties.getTtl(), dMallRedisProperties.getTtlUnitEnum());
        }
    }
}
