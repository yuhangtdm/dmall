package com.dmall.common.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 基础返回状态枚举类
 * @author: created by hang.yu on 2019/11/7 22:51
 */
@Getter
@AllArgsConstructor
public enum BasicStatusEnum implements ErrorCodeEnum {

    /**
     * 请求成功
     */
    SUCCESS("0", "请求成功"),

    /**
     * 服务器忙，请稍后再试
     */
    FAIL("500", "服务器忙，请稍后再试"),

    /**
     * 请求数据不合法
     */
    BAD_REQUEST("400", "请求数据不合法"),

    /**
     * 请求路径不合法
     */
    NOT_FOUND_REQUEST("404", "请求路径不合法"),

    /**
     * 请求参数类型不合法
     */
    PARAM_TYPE_ERROR("409", "请求参数类型不合法"),

    /**
     * 请求体不合法
     */
    MEDIA_PARAM_TYPE_ERROR("406", "请求体不合法"),

    /**
     * 请求方式不合法
     */
    METHOD_NOT_ALLOWED("405", "请求方式不合法"),

    /**
     * 用户未登录
     */
    USER_NOT_LOGIN("408", "用户未登录"),

    /**
     * 权限不足
     */
    USER_NOT_ALLOW("403", "权限不足,请联系管理员");

    /**
     * 返回码
     */
    private final String code;

    /**
     * 描述
     */
    private final String msg;

}
