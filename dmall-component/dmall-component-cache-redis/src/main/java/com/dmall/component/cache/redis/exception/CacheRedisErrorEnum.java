package com.dmall.component.cache.redis.exception;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: created by yuhang on 2019/11/12 23:42
 */
@Getter
@AllArgsConstructor
public enum CacheRedisErrorEnum implements ErrorCodeEnum {

    BASIC_ERROR("REDIS-001","连接redis服务器错误"),
    NO_CACHE_PREFIX("REDIS-002","缓存前缀为空"),
    ;

    private String code;

    private String msg;
}
