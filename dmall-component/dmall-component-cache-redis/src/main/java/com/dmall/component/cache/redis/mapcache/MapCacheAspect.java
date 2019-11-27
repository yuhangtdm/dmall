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
            String key;

            if (StrUtil.isNotBlank(mapCacheable.key())) {
                key = mapCacheable.key();
            } else {
                String env = environment.getActiveProfiles()[0];
                key = dMallRedisProperties.getCacheKeyPrefix() + StrUtil.UNDERLINE + env
                        + StrUtil.UNDERLINE + mapCacheable.cacheNames() + StrUtil.COLON + className + StrUtil.COLON + methodName;
            }

            List values = dmallRedisTemplate.opsForHash().values(key);
            if (CollUtil.isNotEmpty(values)) {
                log.info("cache hit,key:{}", key);
                return values;
            } else {
                log.info("cache miss,key:{}", key);
                Object result = joinPoint.proceed(args);
                List list = (List) result;
                for (Object o : list) {
                    Long id = (Long) ReflectUtil.getFieldValue(o, "id");
                    dmallRedisTemplate.opsForHash().put(key, id, o);
                    if (mapCacheable.timeout() > 0L && mapCacheable.timeUnit() != null){
                        dmallRedisTemplate.expire(key, mapCacheable.timeout(),mapCacheable.timeUnit());
                    }else {
                        dmallRedisTemplate.expire(key, dMallRedisProperties.getTtl(),dMallRedisProperties.getTtlUnitEnum());
                    }
                }
                return result;
            }

        } catch (Throwable e) {
            log.warn("MapCacheable method:{} catch error", methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_CACHE_ABLE_ERROR);
        }
    }

    /**
     * map缓存切面方法 用于获取对象的方法
     */
    @Around("mapGetCache()")
    public Object mapGetCache(ProceedingJoinPoint joinPoint) {
        String methodName = "";
        try {

            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapGetCache mapGetCache = methodSignature.getMethod().getAnnotation(MapGetCache.class);
            String key;
            String className = joinPoint.getTarget().getClass().getName();
            if (StrUtil.isBlank(mapGetCache.key())) {
                String env = environment.getActiveProfiles()[0];
                key = dMallRedisProperties.getCacheKeyPrefix() + StrUtil.UNDERLINE + env
                        + StrUtil.UNDERLINE + mapGetCache.cacheName() + StrUtil.COLON + className
                        + StrUtil.COLON + methodName;
            } else {
                key = mapGetCache.key();
            }

            Long id = (Long) ReflectUtil.getFieldValue(args[0], Constants.ID);
            Object cacheResult = dmallRedisTemplate.opsForHash().get(key, id);
            if (cacheResult != null) {
                log.info("cache hit,key:{}", key);
                return cacheResult;
            } else {
                log.info("cache miss,key:{}", key);
                Object result = joinPoint.proceed(args);
                dmallRedisTemplate.opsForHash().put(key, id, result);
                return result;
            }

        } catch (Throwable e) {
            log.warn("MapGetCache method:{} catch error", methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_CACHE_ABLE_ERROR);
        }
    }

    /**
     * map缓存切面方法 用于更新对象的方法
     */
    @Around("mapPutCache()")
    public Object mapPutCache(ProceedingJoinPoint joinPoint) {
        String methodName = "";
        try {

            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapPutCache mapPutCache = methodSignature.getMethod().getAnnotation(MapPutCache.class);
            String className = joinPoint.getTarget().getClass().getName();
            String key = StrUtil.isNotBlank(mapPutCache.key()) ? mapPutCache.key()
                    : dMallRedisProperties.getCacheKeyPrefix() + StrUtil.UNDERLINE + environment.getActiveProfiles()[0]
                    + StrUtil.UNDERLINE + mapPutCache.cacheName() + StrUtil.COLON + className
                    + StrUtil.COLON + methodName;
            Object result = joinPoint.proceed(args);
            dmallRedisTemplate.opsForHash().put(key, result, args[0]);
            if (mapPutCache.timeout() > 0L && mapPutCache.timeUnit() != null){
                dmallRedisTemplate.expire(key, mapPutCache.timeout(),mapPutCache.timeUnit());
            }else {
                dmallRedisTemplate.expire(key, dMallRedisProperties.getTtl(),dMallRedisProperties.getTtlUnitEnum());
            }
            return result;
        } catch (Throwable e) {
            log.warn("MapPutCache method:{} catch error", methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_CACHE_ABLE_ERROR);
        }
    }

    /**
     * map切面缓存方法 用于删除缓存的方法
     */
    @AfterReturning(pointcut = "mapDeleteCache()", returning = "result")
    public Object mapDeleteCache(JoinPoint joinPoint, Object result) {
        String methodName = "";
        try {
            String className = joinPoint.getTarget().getClass().getName();
            methodName = joinPoint.getSignature().getName();
            Class<?> tClass = joinPoint.getTarget().getClass();
            Method[] declaredMethods = tClass.getDeclaredMethods();
            String key = null;
            for (Method method : declaredMethods) {
                if (method.getName().equals(methodName)) {
                    MapDeleteCache mapDeleteCache = method.getAnnotation(MapDeleteCache.class);
                    key = StrUtil.isBlank(mapDeleteCache.key()) ?
                            dMallRedisProperties.getCacheKeyPrefix() + StrUtil.UNDERLINE + environment.getActiveProfiles()[0]
                                    + StrUtil.UNDERLINE + mapDeleteCache.cacheName() + StrUtil.COLON + className
                                    + StrUtil.COLON + methodName
                            : mapDeleteCache.key();
                }
            }

            Object cacheResult = dmallRedisTemplate.opsForHash().get(key, result);
            if (cacheResult != null) {
                dmallRedisTemplate.opsForHash().delete(key, result);
                log.info("delete cache success,key:{},hashKey:{}", key, result);
            }

        } catch (Exception e) {
            log.warn("the method:{} unsuited @MapDeleteCache", methodName);
        }
        return result;
    }
}
