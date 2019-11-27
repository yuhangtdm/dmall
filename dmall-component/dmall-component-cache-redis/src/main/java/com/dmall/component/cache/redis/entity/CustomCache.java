package com.dmall.component.cache.redis.entity;

import com.dmall.component.cache.redis.enums.TTLUnitEnum;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @description: 自定义缓存类
 * @author: created by hang.yu on 2019/11/3 22:13
 */
@Data
public class CustomCache {

    /**
     * 缓存名称
     */
    private String name;

    /**
     * 缓存过期时间 默认一天
     */
    private Long ttl = 1L;

    /**
     * 缓存过期单位 默认为天
     */
    private TimeUnit ttlUnitEnum = TimeUnit.DAYS;
}
