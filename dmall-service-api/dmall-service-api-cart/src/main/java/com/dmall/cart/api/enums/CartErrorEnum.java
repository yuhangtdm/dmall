package com.dmall.cart.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 购物车错误枚举 1300开头
 * @author: created by hang.yu on 2020-03-11 22:46:56
 */
@Getter
@AllArgsConstructor
public enum CartErrorEnum implements ErrorCodeEnum {

    /**
     * 购物车列表为空
     */
    CART_LIST_EMPTY("1300", "购物车列表为空");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误描述
     */
    private final String msg;

}
