package com.dmall.bms.service.impl.datadictionary.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 数据字典错误枚举
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Getter
@AllArgsConstructor
public enum DataDictionaryErrorEnum implements ErrorCodeEnum {

    SAVE_DATADICTIONARY_ERROR("dataDictionary-001", "新增数据字典失败"),
    UPDATE_DATADICTIONARY_ERROR("dataDictionary _002", "修改数据字典失败"),
    DELETE_DATADICTIONARY_ERROR("dataDictionary_003", "删除数据字典失败"),
    DATADICTIONARY_NOT_EXIST("dataDictionary_004", "该数据字典不存在"),

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
