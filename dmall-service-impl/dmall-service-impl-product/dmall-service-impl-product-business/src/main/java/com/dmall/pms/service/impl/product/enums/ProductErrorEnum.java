package com.dmall.pms.service.impl.product.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商品错误枚举
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Getter
@AllArgsConstructor
public enum  ProductErrorEnum implements ErrorCodeEnum {

    CATEGORY_NOT_EXISTS("product-001","商品分类不存在"),
    CATEGORY_LEVEL_ERROR("product-002","商品分类级别不合法"),
    BRAND_NOT_EXISTS("product-003","品牌不存在"),
    CATEGORY_BRAND_ERROR("product-004","商品分类和品牌不匹配"),

    SPECIFICATIONS_ATTRIBUTE_TYPE_NOT_EXISTS("product-005","销售规格分类不存在"),
    CATEGORY_ATTRIBUTE_TYPE_ERROR("product-006","商品分类和销售规格分类不匹配"),
    ATTRIBUTE_NOT_EXISTS("product-007","销售规格属性不存在"),
    ATTRIBUTE_TYPE_ATTRIBUTE_ERROR("product-008","销售规格分类和销售规格属性不匹配"),

    PARAMS_ATTRIBUTE_TYPE_NOT_EXISTS("product-009","销售参数分类不存在"),
    PARAMS_CATEGORY_ATTRIBUTE_TYPE_ERROR("product-010","商品分类和销售参数分类不匹配"),
    PARAMS_ATTRIBUTE_NOT_EXISTS("product-011","销售参数属性不存在"),

    PRODUCT_NAME_EXISTS("product _012","商品名称已存在"),
    DELETE_PRODUCT_ERROR("product_003","删除商品失败"),
    PRODUCT_NOT_EXIST("product_004","该商品不存在"),

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
