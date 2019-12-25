package com.dmall.pms.service.impl.attributetype.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性分类错误枚举
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Getter
@AllArgsConstructor
public enum AttributeTypeErrorEnum implements ErrorCodeEnum {

    SAVE_ATTRIBUTE_TYPE_ERROR("attributeType_001", "属性分类名称已存在"),
    ATTRIBUTE_TYPE_NOT_EXIST("attributeType_002", "该属性分类不存在"),
    ATTRIBUTE_TYPE_HAS_ATTRIBUTE("attributeType_003", "该属性分类下有属性,不可删除"),
    SPECIFICATIONS_EXISTS("attributeType_004", "销售规格已存在");

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String msg;

}
