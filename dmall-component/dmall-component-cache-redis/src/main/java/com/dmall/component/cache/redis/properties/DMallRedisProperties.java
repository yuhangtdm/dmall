package com.dmall.component.cache.redis.properties;

import com.dmall.component.cache.redis.entity.CustomCache;
import com.dmall.component.cache.redis.enums.TTLUnitEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @description: 缓存属性配置
 * @author: created by yuhang on 2019/11/3 21:50
 */
@Data
@ConfigurationProperties(prefix = "dmall.cache.redis")
public class DMallRedisProperties {

    /**
     * 是否可用
     */
    private Boolean enabled = Boolean.TRUE;

    /**
     * 缓存前缀
     */
    private String cacheKeyPrefix = "";


    /**
     * 过期单位,默认是天
     */
    private TTLUnitEnum ttlUnitEnum = TTLUnitEnum.DAY;

    /**
     * 默认过期时间 默认是1天
     */
    private Long ttl = 1L;

    /**
     * 自定义缓存集合
     */
    private List<CustomCache> customCaches;

}
