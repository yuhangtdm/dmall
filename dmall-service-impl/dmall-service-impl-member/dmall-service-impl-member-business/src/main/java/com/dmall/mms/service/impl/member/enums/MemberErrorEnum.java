package com.dmall.mms.service.impl.member.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@Getter
@AllArgsConstructor
public enum  MemberErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBER_ERROR("member-001","新增会员失败"),
    UPDATE_MEMBER_ERROR("member _002","修改会员失败"),
    DELETE_MEMBER_ERROR("member_003","删除会员失败"),
    MEMBER_NOT_EXIST("member_004","该会员不存在"),

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
