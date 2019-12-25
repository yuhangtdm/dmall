package com.dmall.pms.service.impl.category.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商品分类错误枚举
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Getter
@AllArgsConstructor
public enum  CategoryErrorEnum implements ErrorCodeEnum {

    CATEGORY_NOT_EXIST("category_001","该分类不存在"),
    PARENT_CATEGORY_NOT_EXIST("category_002","上级分类不存在"),
    PARENT_LEVEL_ERROR("category_003","该分类级别不合法"),

    /**
     * 删除的校验
     */
    CONTAINS_SKU_ERROR("category_004","该分类下有sku,不允许删除"),
    CONTAINS_ATTRIBUTE_TYPE_ERROR("category_005","该分类下有属性分类,不允许删除"),
    CONTAINS_ATTRIBUTE_ERROR("category_006","该分类下有属性,不允许删除"),
    CONTAINS_SUB_CATEGORY_ERROR("category_007","该分类下子分类,不允许删除"),

    /**
     * 设置品牌的校验
     */
    BRAND_ID_EMPTY("category_008","品牌列表不能为空"),
    BRAND_IDS_REPEATED("category_010","品牌列表不能重复"),
    BRAND_ID_INVALID("category_009","品牌不存在"),

    /**
     * 设置属性分类的校验
     */
    ATTRIBUTE_TYPE_ID_EMPTY("category_011","属性分类列表不能为空"),
    ATTRIBUTE_TYPE_ID_REPEATED("category_012","属性分类列表不能重复"),
    ATTRIBUTE_TYPE_ID_INVALID("category_013","属性分类不存在"),

    /**
     * 设置属性的校验
     */
    ATTRIBUTE_ID_EMPTY("category_014","属性列表不能为空"),
    ATTRIBUTE_ID_REPEATED("category_015","属性列表不能重复"),
    ATTRIBUTE_ID_INVALID("category_016","属性不存在"),



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
