package com.dmall.bms.api.dto.permission.enums;

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

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE")

    ;

    private String code;


    @Override
    public String getCode() {
        return code;
    }

}
