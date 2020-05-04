package com.dmall.mms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: MmsErrorEnum 1200开头
 * @author: created by hang.yu on 2020/4/22 23:39
 */
@Getter
@AllArgsConstructor
public enum MmsErrorEnum implements ErrorCodeEnum {

    /**
     * 邮件已发送
     */
    EMAIL_SEND("1200", "邮件已发送"),

    /**
     * 该邮箱已注册
     */
    EMAIL_EXISTS("1201", "该邮箱已注册"),

    /**
     * 验证码已过期
     */
    CHECK_CODE_EXPIRED("1202", "验证码已过期"),

    /**
     * 验证码错误
     */
    CHECK_CODE_ERROR("1203", "验证码错误"),

    /**
     * 该手机号已注册
     */
    REGISTER_PHONE_EXISTS("1204", "该手机号已注册"),

    /**
     * 该会员名已注册
     */
    REGISTER_NAME_EXISTS("1205", "该会员名已注册"),

    /**
     * 邮箱不正确
     */
    EMAIL_ERROR("1206", "邮箱不正确"),

    /**
     * 原密码错误
     */
    PASSWORD_ERROR("1207", "原密码错误"),

    /**
     * 个人名称不能为空
     */
    PERSONAL_NAME_EMPTY("1210", "个人名称不能为空"),

    /**
     * 单位名称不能为空
     */
    COMPANY_NAME_EMPTY("1211", "单位名称不能为空"),

    /**
     * 纳税人识别号不能为空
     */
    CUSTOMER_TAX_NUMBER_EMPTY("1212", "纳税人识别号不能为空"),

    /**
     * 该发票不存在
     */
    INVOICE_NOT_EXISTS("1213", "该发票不存在"),

    /**
     * 该会员没有发票
     */
    MEMBER_INVOICE_EXISTS("1214", "该会员没有发票"),

    /**
     * 收货地址最多只有20个
     */
    MEMBER_RECEIVE_ADDRESS_COUNT_LIMIT("1215", "收货地址最多只有20个"),

    /**
     * 该会员收货地址不存在
     */
    MEMBER_RECEIVE_ADDRESS_NOT_EXIST("1216", "该会员收货地址不存在"),

    /**
     * 您不能操作他人的地址
     */
    MEMBER_RECEIVE_ADDRESS_UPDATE_ERROR("1217", "您不能操作他人的地址"),

    /**
     * 该会员收藏的sku不存在
     */
    MEMBER_COLLECTION_SKU_NOT_EXIST("1218", "该会员收藏的sku不存在"),

    /**
     * 您不可取消他人收藏的sku
     */
    MEMBER_COLLECTION_SKU_ERROR("1219", "您不可取消他人收藏的sku"),

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
