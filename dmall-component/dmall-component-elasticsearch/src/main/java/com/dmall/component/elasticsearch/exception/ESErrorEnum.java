package com.dmall.component.elasticsearch.exception;

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

    BASIC_ERROR("ES-001", "连接es服务器错误"),
    NO_INDEX_NAME("ES-002", "索引名称为空"),
    NO_TYPE_NAME("ES-003", "类型名称为空"),
    NO_ID_FIELD("ES-004", "实体无id属性"),
    NO_ID("ES-005", "ID为空"),
    NO_SEARCH("ES-006", "搜索对象为空");

    private String code;

    private String msg;

}
