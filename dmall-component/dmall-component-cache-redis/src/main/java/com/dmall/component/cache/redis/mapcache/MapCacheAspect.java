package com.dmall.component.cache.redis.mapcache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dmall.common.constants.Constants;
import com.dmall.common.model.exception.ComponentException;
import com.dmall.common.util.FieldUtil;
import com.dmall.common.util.ObjectUtil;
import com.dmall.component.cache.redis.exception.CacheRedisErrorEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.List;

/**
 * @description: mapCache切面
 * 适用于数据量不大的db实体缓存,缓存内容是map,hashKey取id,hashValue取实体对象
 * @author: created by hang.yu on 2019/11/23 22:35
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class MapCacheAspect {

    private final MapCacheUtil mapCacheUtil;

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapListCache)")
    public void mapListCache() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapGetCache)")
    public void mapGetCache() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapDeleteCache)")
    public void mapDeleteCache() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapPostCache)")
    public void mapPostCache() {
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapPutCache)")
    public void mapPutCache() {
    }

    /**
     * map缓存切面方法 用于获取list的方法
     * 适用于获取所有数据
     */
    @Around("mapListCache()")
    public Object mapListCache(ProceedingJoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String className = joinPoint.getTarget().getClass().getName();
            MapCacheable mapCacheable = validate(joinPoint);
            MapListCache mapListCache = methodSignature.getMethod().getAnnotation(MapListCache.class);
            String key = ObjectUtil.or(StrUtil.isNotBlank(mapListCache.key()), mapListCache.key(),
                    mapCacheUtil.getKey(mapCacheable.cacheNames(), className));
            List<Object> values = mapCacheUtil.values(key);
            if (CollUtil.isNotEmpty(values)) {
                log.info("cache hit,key:{}", key);
                return values;
            } else {
                log.info("cache miss,key:{}", key);
                Object result = joinPoint.proceed(args);
                List<Object> list = (List) result;
                for (Object o : list) {
                    Long id = getIdValue(o);
                    if (id != null) {
                        mapCacheUtil.put(key, String.valueOf(id), o, mapListCache.timeout(), mapListCache.timeUnit());
                    }
                }
                return result;
            }

        } catch (Throwable e) {
            throw new ComponentException(CacheRedisErrorEnum.MAP_LIST_CACHE_ERROR);
        }
    }

    /**
     * map缓存切面方法 用于获取对象的方法
     * 入参为id
     */
    @Around("mapGetCache()")
    public Object mapGetCache(ProceedingJoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapCacheable mapCacheable = validate(joinPoint);
            MapGetCache mapGetCache = methodSignature.getMethod().getAnnotation(MapGetCache.class);
            String className = joinPoint.getTarget().getClass().getName();
            String key = ObjectUtil.or(StrUtil.isNotBlank(mapGetCache.key()), mapGetCache.key(),
                    mapCacheUtil.getKey(mapCacheable.cacheNames(), className));
            Object cacheResult = mapCacheUtil.get(key, String.valueOf(args[0]));
            if (cacheResult != null) {
                log.info("cache hit,key:{},hashKey:{}", key, args[0]);
                return cacheResult;
            } else {
                log.info("cache miss,key:{},hashKey:{}", key, args[0]);
                Object result = joinPoint.proceed(args);
                mapCacheUtil.put(key, String.valueOf(args[0]), result, mapGetCache.timeout(), mapGetCache.timeUnit());
                return result;
            }

        } catch (Throwable e) {
            throw new ComponentException(CacheRedisErrorEnum.MAP_GET_CACHE_ERROR);
        }
    }

    /**
     * map缓存切面方法 用于新增或更新对象的方法
     */
    @Around("mapPostCache()")
    public Object mapPostCache(ProceedingJoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapCacheable mapCacheable = validate(joinPoint);
            MapPostCache mapPostCache = methodSignature.getMethod().getAnnotation(MapPostCache.class);
            String key = StrUtil.isBlank(mapPostCache.key()) ? mapCacheUtil.getKey(mapCacheable.cacheNames(),
                    joinPoint.getTarget().getClass().getName()) : mapPostCache.key();
            Object result = joinPoint.proceed(args);
            // 取入参的id
            Long id = getIdValue(args[0]);
            if (id != null) {
                mapCacheUtil.put(key, String.valueOf(id), args[0], mapPostCache.timeout(), mapPostCache.timeUnit());
            }
            return result;
        } catch (Throwable e) {
            throw new ComponentException(CacheRedisErrorEnum.MAP_POST_CACHE_ERROR);
        }
    }

    /**
     * map缓存切面方法 用于新增或更新对象的方法
     */
    @Around("mapPutCache()")
    public Object mapPutCache(ProceedingJoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapPutCache mapPutCache = methodSignature.getMethod().getAnnotation(MapPutCache.class);
            MapCacheable mapCacheable = validate(joinPoint);
            String className = joinPoint.getTarget().getClass().getName();
            String key = ObjectUtil.or(StrUtil.isNotBlank(mapPutCache.key()), mapPutCache.key(),
                    mapCacheUtil.getKey(mapCacheable.cacheNames(), className));
            Object result = joinPoint.proceed(args);
            // 取返回值的id
            Long id = getIdValue(result);
            if (id != null) {
                mapCacheUtil.put(key, String.valueOf(id), result, mapPutCache.timeout(), mapPutCache.timeUnit());
            }
            return result;
        } catch (Throwable e) {
            throw new ComponentException(CacheRedisErrorEnum.MAP_PUT_CACHE_ERROR);
        }
    }


    /**
     * map切面缓存方法 用于删除缓存的方法
     */
    @Around("mapDeleteCache()")
    public Object mapDeleteCache(ProceedingJoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            MapCacheable mapCacheable = validate(joinPoint);
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MapDeleteCache mapDeleteCache = methodSignature.getMethod().getAnnotation(MapDeleteCache.class);
            String key = ObjectUtil.or(StrUtil.isNotBlank(mapDeleteCache.key()), mapDeleteCache.key(),
                    mapCacheUtil.getKey(mapCacheable.cacheNames(), joinPoint.getTarget().getClass().getName()));
            Object result = joinPoint.proceed(args);
            mapCacheUtil.delete(key, String.valueOf(args[0]));
            log.info("delete cache success,key:{},hashKey:{}", key, args[0]);
            return result;
        } catch (Throwable e) {
            throw new ComponentException(CacheRedisErrorEnum.MAP_DELETE_CACHE_ERROR);
        }
    }

    /**
     * 获取id值
     */
    private Long getIdValue(Object result) {
        TableId tableId = FieldUtil.findAnnotationField(result, TableId.class);
        Long id = null;
        if (tableId != null) {
            String value = tableId.value();
            id = (Long) ReflectUtil.getFieldValue(result, value);
        } else {
            id = (Long) ReflectUtil.getFieldValue(result, Constants.ID);
        }
        return id;
    }

    /**
     * 校验MapCacheable不为空
     */
    private MapCacheable validate(ProceedingJoinPoint joinPoint) {
        MapCacheable mapCacheable = joinPoint.getTarget().getClass().getAnnotation(MapCacheable.class);
        if (mapCacheable == null) {
            throw new ComponentException(CacheRedisErrorEnum.MAP_SERVICE_ERROR);
        }
        return mapCacheable;
    }


}
