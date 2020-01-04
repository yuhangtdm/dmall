package com.dmall.component.file.qiniu.exception;

import com.dmall.common.enums.base.ErrorCodeEnum;
import com.dmall.common.model.exception.ComponentException;

/**
 * @description: 七牛云自定义异常
 * @author: created by hang.yu on 2019/12/17 10:39
 */
public class QiNiuException extends ComponentException {

    public QiNiuException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getCode(), errorCodeEnum.getMsg());
    }

    public QiNiuException(String code, String msg) {
        super(code, msg);
    }
}

