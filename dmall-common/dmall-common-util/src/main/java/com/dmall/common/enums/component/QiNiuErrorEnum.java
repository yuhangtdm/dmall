package com.dmall.common.enums.component;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: QiNiuErrorEnum 50200开头
 * @author: created by hang.yu on 2019/12/17 10:41
 */
@Getter
@AllArgsConstructor
public enum QiNiuErrorEnum implements ErrorCodeEnum {

    /**
     * 七牛云服务器异常
     */
    QI_NIU_ERROR_ENUM("50200", "七牛云服务器异常"),

    /**
     * 七牛云配置有误
     */
    QI_NIU_CONFIG_ERROR("50201", "七牛云配置有误"),
    ;

    private final String code;

    private final String msg;

}
