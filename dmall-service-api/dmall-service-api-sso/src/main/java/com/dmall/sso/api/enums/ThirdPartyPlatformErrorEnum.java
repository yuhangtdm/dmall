package com.dmall.sso.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: created by hang.yu on 2020/3/1 21:58
 */
@Getter
@AllArgsConstructor
public enum ThirdPartyPlatformErrorEnum implements ErrorCodeEnum {

    WEI_BO_ERROR("1000", "微博暂时无法正常使用"),
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