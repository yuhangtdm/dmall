package com.dmall.framework.zuul;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: AdminUserErrorEnum
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Getter
@AllArgsConstructor
public enum AdminUserErrorEnum implements ErrorCodeEnum {


    /**
     * source不能为空
     */
    SOURCE_NOT_BLANK("60001", "source不能为空"),
    ;

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误描述
     */
    private final String msg;

}
