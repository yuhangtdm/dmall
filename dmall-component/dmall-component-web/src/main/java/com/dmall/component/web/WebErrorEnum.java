package com.dmall.component.web;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: web组件异常枚举
 * @author: created by hang.yu on 2019/11/15 23:12
 */
@Getter
@AllArgsConstructor
public enum WebErrorEnum implements ErrorCodeEnum {

    NO_POINTCUT("WEB-001", "未配置日志切面的包扫描路径"),
    NO_BASE_PACKAGE("WEB-002", "未配置swagger的包扫描路径"),
    ;

    private String code;

    private String msg;

}
