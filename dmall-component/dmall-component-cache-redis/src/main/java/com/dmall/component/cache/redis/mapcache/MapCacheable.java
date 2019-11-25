package com.dmall.component.cache.redis.mapcache;


import java.lang.annotation.*;

/**
 * @description:
 * @author: created by hang.yu on 2019/11/23 22:32
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MapCacheable {

    String key() default "";

}
