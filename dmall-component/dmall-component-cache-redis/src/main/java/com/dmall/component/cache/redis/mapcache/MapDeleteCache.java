package com.dmall.component.cache.redis.mapcache;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: created by hang.yu on 2019/11/26 23:33
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MapDeleteCache {
    /**
     * 缓存key 优先级最高
     */
    String key() default "";

}
