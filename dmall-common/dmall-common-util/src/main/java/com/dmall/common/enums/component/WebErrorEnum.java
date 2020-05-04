package com.dmall.common.enums.component;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: web组件异常枚举 50400开头
 * @author: created by hang.yu on 2019/11/15 23:12
 */
@Getter
@AllArgsConstructor
public enum WebErrorEnum implements ErrorCodeEnum {

    /**
     * 未配置日志切面的包扫描路径
     */
    NO_POINTCUT("50400", "未配置日志切面的包扫描路径"),

    /**
     * 未配置swagger的包扫描路径
     */
    NO_BASE_PACKAGE("50401", "未配置swagger的包扫描路径"),
    ;

    private final String code;

    private final String msg;

}
