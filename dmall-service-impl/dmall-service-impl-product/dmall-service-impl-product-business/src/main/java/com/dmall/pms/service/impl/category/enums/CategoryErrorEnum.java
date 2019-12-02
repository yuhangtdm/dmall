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

    SAVE_CATEGORY_ERROR("category-001","新增商品分类失败"),
    UPDATE_CATEGORY_ERROR("category _002","修改商品分类失败"),
    DELETE_CATEGORY_ERROR("category_003","删除商品分类失败"),
    CATEGORY_NOT_EXIST("category_004","该商品分类不存在"),
    CATEGORY_NAME_UNIQUE("category_005","商品分类名称已存在"),
    PARENT_CATEGORY_NOT_EXIST("category_006","上级分类不存在"),
    PARENT_LEVEL_ERROR("category_007","分类级别不合法"),
    CONTAINS_PRODUCT_ERROR("category_008","该分类下有商品,不允许删除"),
    CONTAINS_BRAND_ERROR("category_009","该分类下有品牌,不允许删除"),
    CONTAINS_ATTRIBUTE_TYPE_ERROR("category_010","该分类下有属性类型,不允许删除"),
    NOT_ALLOW_UPDATE_PARENT_ID("category_011","不允许修改上级"),
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
