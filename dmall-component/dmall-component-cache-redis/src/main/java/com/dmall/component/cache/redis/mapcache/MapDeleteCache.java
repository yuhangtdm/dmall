package com.dmall.component.cache.redis.mapcache;

import cn.hutool.core.util.StrUtil;

import java.lang.annotation.*;

/**
 * @description: MapDeleteCache
 * @author: created by hang.yu on 2019/11/26 23:33
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MapDeleteCache {

    /**
     * 缓存key
     */
    String key() default StrUtil.EMPTY;

}
