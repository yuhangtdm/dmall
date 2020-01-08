package com.dmall.framework.zuul;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: AdminUserErrorEnum
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Getter
@AllArgsConstructor
public enum AdminUserErrorEnum implements ErrorCodeEnum {

    TOKEN_BLANK("zuul_001", "token不能为空"),
    SOURCE_BLANK("zuul_002", "source不能为空"),
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
