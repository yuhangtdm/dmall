package com.dmall.component.cache.redis.configuration;

import com.alibaba.fastjson.JSON;
import com.dmall.common.model.configuration.BasicConfiguration;
import com.dmall.common.util.ObjectUtil;
import com.dmall.component.cache.redis.enums.TTLUnitEnum;
import com.dmall.component.cache.redis.exception.CacheRedisErrorEnum;
import com.dmall.component.cache.redis.exception.CacheRedisException;
import com.dmall.component.cache.redis.lock.DistributedLockService;
import com.dmall.component.cache.redis.properties.DMallRedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: redis缓存配置类
 * @author: created by hang.yu on 2019/11/3 21:35
 */
@Slf4j
@Configuration
@EnableCaching
@EnableConfigurationProperties(DMallRedisProperties.class)
@ConditionalOnProperty(prefix = "dmall.cache.redis", value = "enabled", havingValue = "true")
public class DMallRedisConfiguration extends CachingConfigurerSupport implements BasicConfiguration {

    private static final String LUA = "lua/DistributedLock.lua";

    @Autowired
    private DMallRedisProperties dMallRedisProperties;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 缓存key生成器
     * prefix:cacheName:类名.方法名:参数
     * 参数: 一个参数 如果do对象 取do的id
     * 如果是其他参数 属性名|属性值
     * 多个参数     属性名|属性值,属性名|属性值
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            String[] cacheNames = method.getAnnotation(Cacheable.class).cacheNames();
            sb.append(cacheNames[0]).append(":");
            // 类名
            sb.append(o.getClass().getName()).append(".").append(method.getName()).append(":");
            if (objects.length > 0) {
                if (objects.length == 1) {
                    Field idField = ReflectionUtils.findField(objects[0].getClass(), "id", Long.class);
                    if (idField != null) {
                        ReflectionUtils.makeAccessible(idField);
                        sb.append("id|").append(ReflectionUtils.getField(idField, objects[0]));
                    } else {
                        if (ObjectUtil.isNotEmpty(objects[0])){
                            //todo 待优化
                            sb.append(getParameterNames(method)[0]).append("|").append(objects[0].toString());
                        }
                    }
                } else {
                    // 参数
                    String[] parameterNames = getParameterNames(method);
                    for (int i = 0; i < objects.length; i++) {
                        if (ObjectUtil.isNotEmpty(objects[i])){
                            sb.append(parameterNames[i]).append("|").append(objects[i].toString());
                        }
                    }
                }
            }

            return sb.toString();
        };
    }

    /**
     * 异常处理，当Redis发生异常时，打印日志，但是程序正常走
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheClearError：", e);
            }
        };
        return cacheErrorHandler;
    }

    /**
     * 构建缓存管理器
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        // 初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        // 构建缓存配置
        RedisCacheConfiguration defaultCacheConfig = buildRedisCacheConfiguration(dMallRedisProperties.getCacheKeyPrefix(),
                dMallRedisProperties.getTtlUnitEnum(), dMallRedisProperties.getTtl());

        // 构建初始化的缓存
        Map<String, RedisCacheConfiguration> initialCacheConfiguration = new HashMap<>();
        if (!CollectionUtils.isEmpty(dMallRedisProperties.getCustomCaches())) {
            dMallRedisProperties.getCustomCaches().stream().filter(customCache -> !StringUtils.isBlank(customCache.getName()))
                    .forEach(customCache -> initialCacheConfiguration.put(customCache.getName(),
                            buildRedisCacheConfiguration(dMallRedisProperties.getCacheKeyPrefix(), customCache.getTtlUnitEnum(), customCache.getTtl())));
        }
        return RedisCacheManager.builder(redisCacheWriter)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(initialCacheConfiguration).build();
    }

    /**
     * 构建分布式锁的lua对象
     */
    @Bean
    public DefaultRedisScript<Boolean> distributedLockRedisScript() {
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(LUA)));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }

    /**
     * 分布式锁对象
     */
    @Bean
    public DistributedLockService distributedLock(StringRedisTemplate stringRedisTemplate, DefaultRedisScript<Boolean> redisScript) {
        return new DistributedLockService(stringRedisTemplate, redisScript);
    }

    /**
     * 构建初始化缓存
     *
     * @param cacheKeyPrefix 缓存前缀
     * @param ttlUnitEnum    过期单位
     * @param ttl            过期时间
     */
    private RedisCacheConfiguration buildRedisCacheConfiguration(String cacheKeyPrefix, TTLUnitEnum ttlUnitEnum, Long ttl) {
        return RedisCacheConfiguration.defaultCacheConfig().prefixKeysWith(cacheKeyPrefix + ":").entryTtl(getDuration(ttlUnitEnum, ttl));
    }

    /**
     * 获取Duration
     */
    private Duration getDuration(TTLUnitEnum ttlUnitEnum, Long ttl) {
        Duration duration = null;
        switch (ttlUnitEnum) {
            case DAY: {
                duration = Duration.ofDays(ttl);
                break;
            }
            case HOUR: {
                duration = Duration.ofHours(ttl);
                break;
            }
            case MINUTE: {
                duration = Duration.ofMinutes(ttl);
                break;
            }
            case SECOND: {
                duration = Duration.ofSeconds(ttl);
                break;
            }
            default: {

            }
        }

        return duration;
    }

    /**
     * 获取方法的形参名称
     */
    private String[] getParameterNames(Method method) {
        Parameter[] parameters = method.getParameters();
        String[] parameterNames = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter param = parameters[i];
            parameterNames[i] = param.getName();
        }
        return parameterNames;
    }

    @Override
    @PostConstruct
    public void check() {
        log.info("init -> [{}],properties:\n{}", "DMallRedisProperties", JSON.toJSONString(dMallRedisProperties, true));
        if (StringUtils.isBlank(dMallRedisProperties.getCacheKeyPrefix())){
            throw new CacheRedisException(CacheRedisErrorEnum.NO_CACHE_PREFIX);
        }
    }
}
