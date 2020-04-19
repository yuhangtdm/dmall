package com.dmall.component.cache.redis.exception;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: redis异常枚举类
 * @author: created by hang.yu on 2019/11/12 23:42
 */
@Getter
@AllArgsConstructor
public enum CacheRedisErrorEnum implements ErrorCodeEnum {

    /**
     * 连接redis服务器错误
     */
    BASIC_ERROR("50000", "redis服务器异常"),

    /**
     * 该服务缓存前缀为空
     */
    EMPTY_CACHE_PREFIX("50001", "该服务缓存前缀为空"),

    /**
     * 获取缓存列表失败
     */
    MAP_LIST_CACHE_ERROR("50002", "获取缓存列表失败"),

    /**
     * 获取缓存对象失败
     */
    MAP_GET_CACHE_ERROR("50003", "获取缓存对象失败"),

    /**
     * 更新缓存失败
     */
    MAP_PUT_CACHE_ERROR("50004", "更新缓存失败"),

    /**
     * 删除缓存失败
     */
    MAP_DELETE_CACHE_ERROR("50005", "删除缓存失败"),

    /**
     * 新增缓存失败
     */
    MAP_POST_CACHE_ERROR("50006", "新增缓存失败"),

    /**
     * 缓存服务类不规范
     */
    MAP_SERVICE_ERROR("50007", "缓存服务类不规范"),

    ;

    private final String code;

    private final String msg;
}
