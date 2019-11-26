package com.dmall.component.cache.redis.mapcache;

import java.lang.annotation.*;

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
     * 缓存key 优先级最高
     */
    String key() default "";

    /**
     * 缓存名称
     */
    String cacheName() default "";

}
