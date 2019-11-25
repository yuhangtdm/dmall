package com.dmall.pms.business.service.category.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: CategoryErrorEnum
 * @author: created by hang.yu on 2019/11/24 15:07
 */
@Getter
@AllArgsConstructor
public enum CategoryErrorEnum implements ErrorCodeEnum {

    SAVE_CATEGORY_ERROR("CATEGORY-001","新增分类失败"),
    CATEGORY_NAME_UNIQUE("CATEGORY-002","品牌名称已存在"),
    UPDATE_CATEGORY_ERROR("CATEGORY-003","修改分类失败"),
    DELETE_CATEGORY_ERROR("CATEGORY-006","删除分类失败"),
    CATEGORY_NOT_EXIST("CATEGORY-007","该分类不存在"),
    PARENT_CATEGORY_NOT_EXIST("CATEGORY-008","上级分类不存在"),
    PARENT_LEVEL_ERROR("CATEGORY-009","分类级别不合法"),
    CONTAINS_PRODUCT_ERROR("CATEGORY-010","该分类下有商品,不允许删除"),
    CONTAINS_BRAND_ERROR("CATEGORY-011","该分类下有品牌,不允许删除"),
    CONTAINS_ATTRIBUTE_TYPE_ERROR("CATEGORY-012","该分类下有属性类型,不允许删除"),
    NOT_ALLOW_UPDATE_PARENT_ID("CATEGORY-013","不允许修改上级"),

    ;

    private String code;

    private String msg;
}
