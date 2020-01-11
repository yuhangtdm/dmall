package com.dmall.bms.service.impl.resource.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 资源错误枚举
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Getter
@AllArgsConstructor
public enum ResourceErrorEnum implements ErrorCodeEnum {

    SAVE_RESOURCE_ERROR("resource-001", "新增资源失败"),
    UPDATE_RESOURCE_ERROR("resource _002", "修改资源失败"),
    DELETE_RESOURCE_ERROR("resource_003", "删除资源失败"),
    RESOURCE_NOT_EXIST("resource_004", "该资源不存在"),

    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String msg;

}
