package com.dmall.component.cache.redis.mapcache;

import cn.hutool.core.util.ObjectUtil;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.cache.redis.properties.DMallRedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @description: mapCache切面
 * @author: created by hang.yu on 2019/11/23 22:35
 */
@Slf4j
@Aspect
public class MapCacheAspect {

    private RedisTemplate redisTemplate;

    private DMallRedisProperties dMallRedisProperties;

    public MapCacheAspect(RedisTemplate redisTemplate, DMallRedisProperties dMallRedisProperties) {
        log.info("MapCacheAspect init success");
        this.redisTemplate = redisTemplate;
        this.dMallRedisProperties = dMallRedisProperties;
    }

    @Pointcut("@annotation(com.dmall.component.cache.redis.mapcache.MapCacheable)")
    public void pointcut() {
    }

    /**
     * map缓存切面方法
     */
    @AfterReturning(pointcut = "pointcut()",returning = "result")
    public Object mapCache(JoinPoint joinPoint, Object result){
        String methodName = "";
        try {
            String cacheName = "";
            String className = joinPoint.getTarget().getClass().getName();

            methodName = joinPoint.getSignature().getName();
            Class<?> tClass = joinPoint.getTarget().getClass();
            Method[] declaredMethods = tClass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (method.getName().equals(methodName)) {
                    MapCacheable annotation = method.getAnnotation(MapCacheable.class);
                    if (StringUtils.isNotBlank(annotation.key())) {
                        cacheName = annotation.key();
                    }
                }
            }
            if (StringUtils.isBlank(cacheName)) {
                cacheName = dMallRedisProperties.getCacheKeyPrefix() + ":" + className + ":" + methodName;
            }

            List list = (List) result;
            for (Object o : list) {
                Field idField = ReflectionUtils.findField(o.getClass(), "id", Long.class);
                if (idField != null) {
                    ReflectionUtils.makeAccessible(idField);
                    redisTemplate.opsForHash().put(cacheName,
                            ReflectionUtils.getField(idField, o), ObjectUtil.serialize(o));
                }
            }
        } catch (Exception e) {
            log.warn("the method:{} unsuited @MapCacheable", methodName);
        }
        return result;
    }
}
