package com.dmall.component.web.exception;

import com.dmall.common.enums.base.ErrorCodeEnum;
import com.dmall.common.model.exception.ComponentException;

/**
 * @description: web组件异常
 * @author: created by hang.yu on 2019/11/15 23:11
 */
public class WebException extends ComponentException {

    public WebException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getCode(), errorCodeEnum.getMsg());
    }

    public WebException(String code, String msg){
        super(code, msg);
    }
}
