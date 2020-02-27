package com.dmall.component.notify.email;

import com.dmall.common.enums.error.ErrorCodeEnum;
import com.dmall.common.model.exception.ComponentException;
import lombok.Getter;

/**
 * @description: redis自定义异常
 * @author: created by hang.yu on 2019/11/12 23:41
 */
@Getter
public class SendEmailException extends ComponentException {

    public SendEmailException() {
        this(SendEmailErrorEnum.BASIC_ERROR);
    }

    public SendEmailException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getCode(), errorCodeEnum.getMsg());
    }

    public SendEmailException(String code, String msg) {
        super(code, msg);
    }

}
