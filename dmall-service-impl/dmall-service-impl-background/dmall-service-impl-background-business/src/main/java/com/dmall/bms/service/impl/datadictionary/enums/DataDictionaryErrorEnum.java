package com.dmall.bms.service.impl.datadictionary.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 数据字典错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:02
 */
@Getter
@AllArgsConstructor
public enum DataDictionaryErrorEnum implements ErrorCodeEnum {

    DATADICTIONARY_NOT_EXIST("dataDictionary_100","该数据字典不存在"),

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
