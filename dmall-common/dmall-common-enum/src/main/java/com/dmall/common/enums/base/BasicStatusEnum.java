package com.dmall.common.enums.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 基础返回状态枚举类
 * @author: created by hang.yu on 2019/11/7 22:51
 */
@Getter
@AllArgsConstructor
public enum  BasicStatusEnum implements ErrorCodeEnum{

    SUCCESS("0","请求成功"),
    FAIL("500","网络异常，请稍后再试"),
    BAD_REQUEST("400","入参不合法,请检查入参后再次调用"),
    NOT_FOUND_REQUEST("404","请检查请求路径"),
    PARAM_TYPE_ERROR("405","请检查请求参数类型"),
    ;

    /**
     * 返回码
     */
    private String code;

    /**
     * 描述
     */
    private String msg;

}
