package com.dmall.component.mybatisplus.generator;

import com.dmall.common.enums.base.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 生成文件类型枚举
 * @author: created by hang.yu on 2020/4/19 18:08
 */
@Getter
@AllArgsConstructor
public enum FileEnum implements CodeEnum<String> {

    /**
     * Service
     */
    SERVICE_API("Service"),

    /**
     * ServiceImpl
     */
    SERVICE_IMPL("ServiceImpl"),

    /**
     * Handler
     */
    HANDLER("Handler"),

    /**
     * RequestDTO
     */
    REQUEST_DTO("RequestDTO"),

    /**
     * ResponseDTO
     */
    RESPONSE_DTO("ResponseDTO"),

    /**
     * DO
     */
    DO("DO");

    private final String code;
}
