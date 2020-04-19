package com.dmall.component.cache.redis.mapcache;

import cn.hutool.core.util.StrUtil;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @description: MapGetCache
 * @author: created by hang.yu on 2019/11/26 23:22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MapGetCache {

    /**
     * 缓存key
     */
    String key() default StrUtil.EMPTY;

    /**
     * 超时时间
     */
    long timeout() default 0L;

    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.DAYS;

}
