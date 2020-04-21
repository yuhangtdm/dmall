package com.dmall.bms.api.enums;

import com.dmall.common.enums.base.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: HttpMethodEnum
 * @author: created by hang.yu on 2020/1/16 23:18
 */
@Getter
@AllArgsConstructor
public enum HttpMethodEnum implements CodeEnum<String> {

    /**
     * GET
     */
    GET("GET"),

    /**
     * POST
     */
    POST("POST"),

    /**
     * PUT
     */
    PUT("PUT"),

    /**
     * DELETE
     */
    DELETE("DELETE");

    private final String code;

}