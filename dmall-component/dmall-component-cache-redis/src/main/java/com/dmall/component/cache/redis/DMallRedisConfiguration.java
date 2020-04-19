package com.dmall.component.cache.redis;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.dmall.common.model.BasicConfiguration;
import com.dmall.common.model.exception.ComponentException;
import com.dmall.common.util.JSONUtil;
import com.dmall.component.cache.redis.entity.CacheKeyGenerator;
import com.dmall.component.cache.redis.exception.CacheRedisErrorEnum;
import com.dmall.component.cache.redis.lock.DistributedLockService;
import com.dmall.component.cache.redis.mapcache.MapCacheAspect;
import com.dmall.component.cache.redis.mapcache.MapCacheUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private Environment environment;

    @Override
    @PostConstruct
    public void check() {
        log.info("init -> [{}],properties:\n{}", "DMallRedisProperties", JSONUtil.toJSONStringPretty(dMallRedisProperties));
        if (dMallRedisProperties.getEnabled() && StringUtils.isBlank(dMallRedisProperties.getCacheKeyPrefix())) {
            throw new ComponentException(CacheRedisErrorEnum.EMPTY_CACHE_PREFIX);
        }
    }

    /**
     * 缓存key生成器
     * prefix:cacheName:类名.方法名:参数
     * 参数: 一个参数 如果do对象 取do的id
     * 如果是其他参数 属性名|属性值
     * 多个参数     属性名|属性值,属性名|属性值
     */
    @Override
    public KeyGenerator keyGenerator() {
        return CacheKeyGenerator::generate;
    }

    /**
     * 异常处理，当Redis发生异常时,统一处理
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        return new CacheErrorHandler() {

            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                throw new ComponentException(CacheRedisErrorEnum.BASIC_ERROR);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                throw new ComponentException(CacheRedisErrorEnum.BASIC_ERROR);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                throw new ComponentException(CacheRedisErrorEnum.BASIC_ERROR);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                throw new ComponentException(CacheRedisErrorEnum.BASIC_ERROR);
            }
        };
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

        // 构建初始化自定义的缓存
        Map<String, RedisCacheConfiguration> initialCacheConfiguration = Maps.newHashMap();
        if (CollUtil.isNotEmpty(dMallRedisProperties.getCustomCaches())) {
            dMallRedisProperties.getCustomCaches().stream().filter(customCache -> !StringUtils.isBlank(customCache.getName()))
                    .forEach(customCache -> initialCacheConfiguration.put(customCache.getName(),
                            buildRedisCacheConfiguration(dMallRedisProperties.getCacheKeyPrefix(), customCache.getTtlUnitEnum(), customCache.getTtl())));
        }
        return RedisCacheManager.builder(redisCacheWriter)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(initialCacheConfiguration).build();
    }

    /**
     * 自定义的RedisTemplate
     * key和 HashKey 设置为字符串类型,且配置了缓存前缀
     * value和HashValue 序列化器为jdk序列化
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(keyPrefixSerializer());
        redisTemplate.setHashKeySerializer(keyPrefixSerializer());
        redisTemplate.setValueSerializer(RedisSerializer.java());
        redisTemplate.setHashValueSerializer(RedisSerializer.java());
        return redisTemplate;
    }

    /**
     * key前缀
     */
    @Bean
    public KeyPrefixSerializer keyPrefixSerializer() {
        return new KeyPrefixSerializer(dMallRedisProperties, environment);
    }

    /**
     * mapCache切面
     */
    @Bean
    public MapCacheAspect mapCacheAspect(RedisTemplate<String, Object> dMallRedisTemplate) {
        return new MapCacheAspect(mapCacheUtil(dMallRedisTemplate));
    }

    /**
     * MapCacheUtil
     */
    @Bean
    public MapCacheUtil mapCacheUtil(RedisTemplate<String, Object> dMallRedisTemplate) {
        return new MapCacheUtil(dMallRedisTemplate, dMallRedisProperties);
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
    public DistributedLockService distributedLock(RedisTemplate redisTemplate, DefaultRedisScript<Boolean> redisScript) {
        return new DistributedLockService(redisTemplate, redisScript);
    }

    /**
     * 构建初始化缓存
     *
     * @param cacheKeyPrefix 缓存前缀
     * @param ttlUnitEnum    过期单位
     * @param ttl            过期时间
     */
    private RedisCacheConfiguration buildRedisCacheConfiguration(String cacheKeyPrefix, TimeUnit ttlUnitEnum, Long ttl) {
        return RedisCacheConfiguration.defaultCacheConfig().prefixKeysWith(StrUtil.format("{}:", cacheKeyPrefix))
                .entryTtl(getDuration(ttlUnitEnum, ttl));
    }

    /**
     * 获取Duration
     */
    private Duration getDuration(TimeUnit ttlUnitEnum, Long ttl) {
        Duration duration = null;
        switch (ttlUnitEnum) {
            case DAYS: {
                duration = Duration.ofDays(ttl);
                break;
            }
            case HOURS: {
                duration = Duration.ofHours(ttl);
                break;
            }
            case MINUTES: {
                duration = Duration.ofMinutes(ttl);
                break;
            }
            case SECONDS: {
                duration = Duration.ofSeconds(ttl);
                break;
            }
            default: {

            }
        }

        return duration;
    }

}
