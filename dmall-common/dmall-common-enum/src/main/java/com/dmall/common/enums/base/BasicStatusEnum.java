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
    FAIL("500","服务器忙，请稍后再试"),
    BAD_REQUEST("400","请求数据不合法"),
    NOT_FOUND_REQUEST("404","请求路径不合法"),
    PARAM_TYPE_ERROR("409","请求参数类型不合法"),
    MEDIA_PARAM_TYPE_ERROR("406","请求体不合法"),
    METHOD_NOT_ALLOWED("405","请求方式不合法"),
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
