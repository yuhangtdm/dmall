package com.dmall.pms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商品分类错误枚举
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Getter
@AllArgsConstructor
public enum CategoryErrorEnum implements ErrorCodeEnum {

    CATEGORY_NOT_EXIST("1001", "该分类不存在"),
    PARENT_CATEGORY_NOT_EXIST("1002", "上级分类不存在"),
    PARENT_LEVEL_ERROR("1003", "该分类级别不合法"),

    /**
     * 删除的校验
     */
    CONTAINS_SKU_ERROR("2001", "该分类有sku,不允许删除"),
    CONTAINS_PRODUCT_ERROR("2002", "该分类有商品,不允许删除"),
    CONTAINS_SUB_CATEGORY_ERROR("2003", "该分类有子分类,不允许删除"),

    /**
     * 设置品牌的校验
     */
    BRAND_IDS_REPEATED("3001", "品牌列表不能重复"),
    BRAND_ID_INVALID("3002", "品牌不存在"),

    /**
     * 设置属性分类的校验
     */
    ATTRIBUTE_TYPE_ID_REPEATED("4001", "属性分类列表不能重复"),
    ATTRIBUTE_TYPE_ID_INVALID("4002", "属性分类不存在"),

    /**
     * 设置属性的校验
     */
    ATTRIBUTE_ID_REPEATED("5001", "属性列表不能重复"),
    ATTRIBUTE_ID_INVALID("5002", "属性不存在"),


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
