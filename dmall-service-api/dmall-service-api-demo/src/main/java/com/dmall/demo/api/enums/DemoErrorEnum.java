package com.dmall.demo.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: DemoErrorEnum
 * @author: created by hang.yu on 2020/4/20 22:42
 */
@Getter
@AllArgsConstructor
public enum DemoErrorEnum implements ErrorCodeEnum {
    ;

    private final String code;

    private final String msg;

}
