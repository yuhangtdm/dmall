package com.dmall.component.cache.redis.mapcache;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @description: MapPostCache
 * @author: created by hang.yu on 2019/11/26 23:33
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MapPostCache {
    /**
     * 缓存key 优先级最高
     */
    String key() default "" ;

    /**
     * 超时时间
     */
    long timeout() default 0L;

    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.DAYS;

}
