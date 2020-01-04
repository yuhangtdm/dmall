package com.dmall.component.cache.redis.mapcache;

import java.lang.annotation.*;

/**
 * @description: MapCacheable
 * @author: created by hang.yu on 2019/11/26 23:22
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MapCacheable {

    /**
     * 缓存名称
     */
    String cacheNames() default "";

}
