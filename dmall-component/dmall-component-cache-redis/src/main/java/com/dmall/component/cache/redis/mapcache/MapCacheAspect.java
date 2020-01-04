package com.dmall.component.cache.redis.mapcache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.component.cache.redis.exception.CacheRedisErrorEnum;
import com.dmall.component.cache.redis.exception.CacheRedisException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @description: mapCache切面
 * @author: created by hang.yu on 2019/11/23 22:35
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class MapCacheAspect {

    private MapCacheUtil mapCacheUtil;

    private RedisTemplate dmallRedisTemplate;

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapListCache)" )
    public void mapListCache() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapGetCache)" )
    public void mapGetCache() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapDeleteCache)" )
    public void mapDeleteCache() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapPostCache)" )
    public void mapPostCache() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapPutCache)" )
    public void mapPutCache() {
    }

    /**
     * map缓存切面方法 用于获取list的方法
     * 适用于获取所有数据
     */
    @Around("mapListCache()" )
    public Object mapListCache(ProceedingJoinPoint joinPoint) {
        String methodName = "" ;
        try {
            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String className = joinPoint.getTarget().getClass().getName();
            MapCacheable mapCacheable = validate(joinPoint);
            MapListCache mapListCache = methodSignature.getMethod().getAnnotation(MapListCache.class);
            String key = StrUtil.isNotBlank(mapListCache.key()) ? mapListCache.key()
                    : mapCacheUtil.getkey(mapCacheable.cacheNames(), className);
            List values = dmallRedisTemplate.opsForHash().values(key);
            if (CollUtil.isNotEmpty(values)) {
                log.info("cache hit,key:{}" , key);
                return values;
            } else {
                log.info("cache miss,key:{}" , key);
                Object result = joinPoint.proceed(args);
                List list = (List) result;
                for (Object o : list) {
                    Long id = (Long) ReflectUtil.getFieldValue(o, Constants.ID);
                    mapCacheUtil.put(key, String.valueOf(id), o, mapListCache.timeout(), mapListCache.timeUnit());
                }
                return result;
            }

        } catch (Throwable e) {
            log.error("MapCacheable method:{} catch error" , methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_CACHE_ABLE_ERROR);
        }
    }

    /**
     * map缓存切面方法 用于获取对象的方法
     * 入参为id
     */
    @Around("mapGetCache()" )
    public Object mapGetCache(ProceedingJoinPoint joinPoint) {
        String methodName = "" ;
        try {

            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapCacheable mapCacheable = validate(joinPoint);
            MapGetCache mapGetCache = methodSignature.getMethod().getAnnotation(MapGetCache.class);
            String className = joinPoint.getTarget().getClass().getName();
            String key = StrUtil.isNotBlank(mapGetCache.key()) ? mapGetCache.key()
                    : mapCacheUtil.getkey(mapCacheable.cacheNames(), className);
            Object cacheResult = dmallRedisTemplate.opsForHash().get(key, String.valueOf(args[0]));
            if (cacheResult != null) {
                log.info("cache hit,key:{},hashKey:{}" , key, args[0]);
                return cacheResult;
            } else {
                log.info("cache miss,key:{},hashKey:{}" , key, args[0]);
                Object result = joinPoint.proceed(args);
                mapCacheUtil.put(key, String.valueOf(args[0]), result, mapGetCache.timeout(), mapGetCache.timeUnit());
                return result;
            }

        } catch (Throwable e) {
            log.error("MapGetCache method:{} catch error" , methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_GET_CACHE_ERROR);
        }
    }

    /**
     * map缓存切面方法 用于新增或更新对象的方法
     */
    @Around("mapPostCache()" )
    public Object mapPostCache(ProceedingJoinPoint joinPoint) {
        String methodName = "" ;
        try {

            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapCacheable mapCacheable = validate(joinPoint);
            MapPostCache mapPostCache = methodSignature.getMethod().getAnnotation(MapPostCache.class);
            String key = StrUtil.isBlank(mapPostCache.key()) ? mapCacheUtil.getkey(mapCacheable.cacheNames(),
                    joinPoint.getTarget().getClass().getName()) : mapPostCache.key();
            Object result = joinPoint.proceed(args);
            Object fieldValue = ReflectUtil.getFieldValue(args[0], Constants.ID);
            mapCacheUtil.put(key, String.valueOf(fieldValue), args[0], mapPostCache.timeout(), mapPostCache.timeUnit());
            return result;
        } catch (Throwable e) {
            log.error("MapPostCache method:{} catch error" , methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_POST_CACHE_ERROR);
        }
    }

    /**
     * map缓存切面方法 用于新增或更新对象的方法
     */
    @Around("mapPutCache()" )
    public Object mapPutCache(ProceedingJoinPoint joinPoint) {
        String methodName = "" ;
        try {
            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapPutCache mapPutCache = methodSignature.getMethod().getAnnotation(MapPutCache.class);
            MapCacheable mapCacheable = validate(joinPoint);
            String className = joinPoint.getTarget().getClass().getName();
            String key = StrUtil.isBlank(mapPutCache.key()) ? mapCacheUtil.getkey(mapCacheable.cacheNames(), className)
                    : mapPutCache.key();
            Object result = joinPoint.proceed(args);
            Object fieldValue = ReflectUtil.getFieldValue(args[0], Constants.ID);
            mapCacheUtil.put(key, String.valueOf(fieldValue), result, mapPutCache.timeout(), mapPutCache.timeUnit());
            return result;
        } catch (Throwable e) {
            log.error("MapPutCache method:{} catch error" , methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_PUT_CACHE_ERROR);
        }
    }

    /**
     * map切面缓存方法 用于删除缓存的方法
     */
    @Around("mapDeleteCache()" )
    public Object mapDeleteCache(ProceedingJoinPoint joinPoint) {
        String methodName = "" ;
        try {
            Object[] args = joinPoint.getArgs();
            MapCacheable mapCacheable = validate(joinPoint);
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapDeleteCache mapDeleteCache = methodSignature.getMethod().getAnnotation(MapDeleteCache.class);
            String key = StrUtil.isBlank(mapDeleteCache.key())
                    ? mapCacheUtil.getkey(mapCacheable.cacheNames(), joinPoint.getTarget().getClass().getName())
                    : mapDeleteCache.key();
            Object result = joinPoint.proceed(args);
            dmallRedisTemplate.opsForHash().delete(key, String.valueOf(args[0]));
            log.info("delete cache success,key:{},hashKey:{}" , key, args[0]);
            return result;
        } catch (Throwable e) {
            log.warn("MapPutCache method:{} catch error" , methodName, e);
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_DELETE_CACHE_ERROR);
        }
    }

    private MapCacheable validate(ProceedingJoinPoint joinPoint) {
        MapCacheable mapCacheable = joinPoint.getTarget().getClass().getAnnotation(MapCacheable.class);
        if (mapCacheable == null) {
            throw new CacheRedisException(CacheRedisErrorEnum.MAP_CACHEABLE_ERROR);
        }
        return mapCacheable;
    }

}
