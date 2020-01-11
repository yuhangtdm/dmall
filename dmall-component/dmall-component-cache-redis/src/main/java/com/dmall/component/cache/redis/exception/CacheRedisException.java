package com.dmall.component.cache.redis.exception;

import com.dmall.common.enums.error.ErrorCodeEnum;
import com.dmall.common.model.exception.ComponentException;
import lombok.Getter;

/**
 * @description: redis自定义异常
 * @author: created by hang.yu on 2019/11/12 23:41
 */
@Getter
public class CacheRedisException extends ComponentException {

    public CacheRedisException() {
        super(CacheRedisErrorEnum.BASIC_ERROR.getCode(), CacheRedisErrorEnum.BASIC_ERROR.getMsg());
    }

    public CacheRedisException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getCode(), errorCodeEnum.getMsg());
    }

    public CacheRedisException(String code, String msg) {
        super(code, msg);
    }

}
