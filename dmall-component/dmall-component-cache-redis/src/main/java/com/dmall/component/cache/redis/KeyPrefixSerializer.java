package com.dmall.component.cache.redis;

import cn.hutool.core.util.StrUtil;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.charset.StandardCharsets;

/**
 * @description: 设置key时增加前缀
 * @author: created by hang.yu on 2020/3/28 15:14
 */
public class KeyPrefixSerializer implements RedisSerializer<String> {

    private DMallRedisProperties dMallRedisProperties;

    private Environment environment;

    public KeyPrefixSerializer(DMallRedisProperties dMallRedisProperties, Environment environment) {
        this.dMallRedisProperties = dMallRedisProperties;
        this.environment = environment;
    }

    /**
     * 反序列化
     */
    @Override
    public String deserialize(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 序列化
     */
    @Override
    public byte[] serialize(String key) {
        String keyPrefix = dMallRedisProperties.getCacheKeyPrefix();
        String env = environment.getActiveProfiles().length == 1 ? environment.getActiveProfiles()[0] : "local";
        String redisKey = StrUtil.format("{}:{}:{}", keyPrefix, env, key);
        return redisKey.getBytes(StandardCharsets.UTF_8);
    }
}
