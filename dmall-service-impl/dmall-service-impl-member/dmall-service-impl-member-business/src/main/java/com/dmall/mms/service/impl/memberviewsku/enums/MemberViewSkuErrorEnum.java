package com.dmall.mms.service.impl.memberviewsku.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员浏览历史记录错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Getter
@AllArgsConstructor
public enum  MemberViewSkuErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBERVIEWSKU_ERROR("memberViewSku-001","新增会员浏览历史记录失败"),
    UPDATE_MEMBERVIEWSKU_ERROR("memberViewSku _002","修改会员浏览历史记录失败"),
    DELETE_MEMBERVIEWSKU_ERROR("memberViewSku_003","删除会员浏览历史记录失败"),
    MEMBERVIEWSKU_NOT_EXIST("memberViewSku_004","该会员浏览历史记录不存在"),

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
