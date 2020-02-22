package com.dmall.mms.service.impl.advice.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员意见表 错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Getter
@AllArgsConstructor
public enum AdviceErrorEnum implements ErrorCodeEnum {

    ADVICE_NOT_EXIST("advice_100","该会员意见表 不存在"),

    ;

   /**
    * 错误码
    */
    private String code;

    /**
     * 错误描述
     */
    private String msg;

}
