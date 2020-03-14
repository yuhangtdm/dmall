package com.dmall.oms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 购物车错误枚举
 * @author: created by hang.yu on 2020-03-11 22:46:56
 */
@Getter
@AllArgsConstructor
public enum CartErrorEnum implements ErrorCodeEnum {

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
