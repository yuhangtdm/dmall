package com.dmall.component.cache.redis.exception;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: redis异常枚举类
 * @author: created by hang.yu on 2019/11/12 23:42
 */
@Getter
@AllArgsConstructor
public enum CacheRedisErrorEnum implements ErrorCodeEnum {

    BASIC_ERROR("REDIS-001", "连接redis服务器错误"),
    NO_CACHE_PREFIX("REDIS-002", "缓存前缀为空"),
    MAP_CACHE_ABLE_ERROR("REDIS-003", "获取缓存列表失败"),
    MAP_GET_CACHE_ERROR("REDIS-004", "获取缓存对象失败"),
    MAP_PUT_CACHE_ERROR("REDIS-005", "更新缓存失败"),
    MAP_DELETE_CACHE_ERROR("REDIS-006", "删除缓存失败"),
    MAP_POST_CACHE_ERROR("REDIS-007", "新增缓存失败"),
    MAP_CACHEABLE_ERROR("REDIS-008", "缓存服务类不规范"),

    ;

    private String code;

    private String msg;
}
