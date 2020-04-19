package com.dmall.common.model.exception;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @description: 组件异常
 * @author: created by hang.yu on 2019/11/7 20:56
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class ComponentException extends RuntimeException {

    private String code;

    private String msg;

    public ComponentException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

    public ComponentException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ComponentException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

}
