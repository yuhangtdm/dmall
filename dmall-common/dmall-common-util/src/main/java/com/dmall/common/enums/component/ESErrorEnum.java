package com.dmall.common.enums.component;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: elasticSearch的错误枚举类
 * @author: created by hang.yu on 2019/11/7 21:41
 */
@Getter
@AllArgsConstructor
public enum ESErrorEnum implements ErrorCodeEnum {

    /**
     * es服务器异常
     */
    BASIC_ERROR("50100", "es服务器异常"),

    /**
     * 索引名称为空
     */
    NO_INDEX_NAME("50101", "索引名称为空"),

    /**
     * 类型名称为空
     */
    NO_TYPE_NAME("50102", "类型名称为空"),

    /**
     * ID为空
     */
    NO_ID("50103", "id为空"),

    /**
     * 存储对象不能为空
     */
    STORE_OBJECT_NULL("50104", "存储对象不能为空"),

    /**
     *
     */
    NO_SEARCH("50105", "搜索对象为空");

    private final String code;

    private final String msg;

}
