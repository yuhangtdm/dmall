package com.dmall.pms.service.impl.categorybrand.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 分类品牌关系错误枚举
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Getter
@AllArgsConstructor
public enum  CategoryBrandErrorEnum implements ErrorCodeEnum {

    SAVE_CATEGORYBRAND_ERROR("categoryBrand-001","新增分类品牌关系失败"),
    UPDATE_CATEGORYBRAND_ERROR("categoryBrand _002","修改分类品牌关系失败"),
    DELETE_CATEGORYBRAND_ERROR("categoryBrand_003","删除分类品牌关系失败"),
    CATEGORYBRAND_NOT_EXIST("categoryBrand_004","该分类品牌关系不存在"),

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
