package com.dmall.pms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: PmsErrorEnum  1100开头
 * @author: created by hang.yu on 2020/4/21 23:41
 */
@Getter
@AllArgsConstructor
public enum PmsErrorEnum implements ErrorCodeEnum {

    /**
     * 商品分类不存在
     */
    CATEGORY_NOT_EXIST("1100", "商品分类不存在"),

    /**
     * 商品分类级别不合法
     */
    CATEGORY_NOT_INVALID("1101", "商品分类级别不合法"),

    /**
     * 该属性参数无效
     */
    ATTRIBUTE_DATA_INVALID("1102", "该属性参数无效"),

    /**
     * 可选值列表不能重复
     */
    INPUT_LIST_INVALID("1103", "可选值列表不能重复"),

    /**
     * 该属性下有商品,不可删除
     */
    CONTAINS_PRODUCT("1104", "该属性下有商品,不可删除"),

    /**
     * 该属性不存在
     */
    ATTRIBUTE_NOT_EXIST("1105", "该属性不存在"),

    /**
     * 公共属性不可改为普通属性
     */
    ATTRIBUTE_TYPE_INVALID("1106", "公共属性不可改为普通属性"),

    /**
     * 该属性类别不存在
     */
    ATTRIBUTE_TYPE_NOT_EXIST("1110", "该属性类别不存在"),

    /**
     * 该属性类别下有商品,不可删除
     */
    ATTRIBUTE_TYPE_HAS_PRODUCT("1111", "该属性类别下有商品,不可删除"),

    /**
     * 该品牌不存在
     */
    BRAND_NOT_EXIST("1120", "该品牌不存在"),

    /**
     * 1121
     */
    CONTAINS_PRODUCT_ERROR("1121", "该品牌下有商品,不允许删除"),

    /**
     * 该品牌名称已存在
     */
    BRAND_NAME_UNIQUE("1122", "该品牌名称已存在"),

    /**
     * 上传品牌logo失败
     */
    UPLOAD_LOGO_ERROR("1123", "上传品牌logo失败"),

    /**
     * 上级分类不存在
     */
    PARENT_CATEGORY_NOT_EXIST("1130", "上级分类不存在"),

    /**
     * 该分类级别不合法
     */
    PARENT_LEVEL_ERROR("1131", "该分类级别不合法"),

    /**
     * 该分类有sku,不允许删除
     */
    CONTAINS_SKU_ERROR("1132", "该分类有sku,不允许删除"),

    /**
     * 该分类有子分类,不允许删除
     */
    CONTAINS_SUB_CATEGORY_ERROR("1133", "该分类有子分类,不允许删除"),

    /**
     * 品牌列表不能重复
     */
    BRAND_IDS_REPEATED("1134", "品牌列表不能重复"),

    /**
     * 品牌不存在
     */
    BRAND_ID_INVALID("1135", "品牌不存在"),

    /**
     * 属性类别列表不能重复
     */
    ATTRIBUTE_TYPE_ID_REPEATED("1136", "属性类别列表不能重复"),

    /**
     * 属性类别不存在
     */
    ATTRIBUTE_TYPE_ID_INVALID("1137", "属性类别不存在"),

    /**
     * 属性列表不能重复
     */
    ATTRIBUTE_ID_REPEATED("1138", "属性列表不能重复"),

    /**
     * 属性不存在
     */
    ATTRIBUTE_ID_INVALID("1139", "属性不存在"),

    /**
     * 价格发生改变,请重新下单
     */
    SKU_PRICE_CHANGE("1040", "价格发生改变,请重新下单"),

    /**
     * 商品总价不允许修改,请重新下单
     */
    SKU_TOTAL_PRICE_CHANGE("1041", "商品总价不允许修改,请重新下单"),

    /**
     * 运费不允许修改,请重新下单
     */
    FREIGHT_CHANGE("1042", "运费不允许修改,请重新下单"),

    /**
     * 订单总价不允许修改,请重新下单
     */
    ORDER_PRICE_CHANGE("1043", "订单总价不允许修改,请重新下单"),

    /**
     * 很抱歉,库存不足
     */
    INSUFFICIENT_INVENTORY("1044", "很抱歉,库存不足"),

    /**
     * 该商品评价不存在
     */
    COMMENT_NOT_EXIST("1050", "该商品评价不存在"),

    /**
     * 商品分类级别不合法
     */
    CATEGORY_LEVEL_ERROR("1060", "商品分类级别不合法"),

    /**
     * 商品分类重复
     */
    CATEGORY_NOT_REPEATED("1061", "商品分类重复"),

    /**
     * 商品名称已存在
     */
    PRODUCT_NAME_EXISTS("1062", "商品名称已存在"),

    /**
     * 删除商品失败
     */
    DELETE_PRODUCT_ERROR("1063", "删除商品失败"),

    /**
     * 该商品不存在
     */
    PRODUCT_NOT_EXISTS("1064", "该商品不存在"),

    /**
     * 上传商品主图失败
     */
    UPLOAD_PRODUCT_ERROR("1065", "上传商品主图失败"),

    /**
     * 上传商品规格配图失败
     */
    UPLOAD_PRODUCT_ATTRIBUTE_ERROR("1066", "上传商品规格配图失败"),

    /**
     * 该sku名称已存在
     */
    SKU_NAME_EXISTS("1070", "该sku名称已存在"),

    /**
     * 该sku不存在
     */
    SKU_NOT_EXISTS("1071", "该sku不存在"),

    /**
     * 媒体列表为空
     */
    MEDIA_NOT_EXIST("1072", "媒体列表为空"),

    ;

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误描述
     */
    private final String msg;
}
