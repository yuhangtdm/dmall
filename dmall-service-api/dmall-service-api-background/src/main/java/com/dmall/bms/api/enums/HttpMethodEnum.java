package com.dmall.bms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: HttpMethodEnum
 * @author: created by hang.yu on 2020/1/16 23:18
 */
@Getter
@AllArgsConstructor
public enum HttpMethodEnum implements CodeDescEnum<String> {

    /**
     * GET
     */
    GET("GET", "get请求"),

    /**
     * POST
     */
    POST("POST", "post请求"),

    /**
     * PUT
     */
    PUT("PUT", "put请求"),

    /**
     * DELETE
     */
    DELETE("DELETE", "delete请求");

    private final String code;

    private final String desc;

}