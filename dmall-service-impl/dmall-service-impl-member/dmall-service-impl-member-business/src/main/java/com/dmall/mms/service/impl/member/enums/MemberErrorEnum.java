package com.dmall.mms.service.impl.member.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Getter
@AllArgsConstructor
public enum MemberErrorEnum implements ErrorCodeEnum {

    MEMBER_NOT_EXIST("member_100","该会员不存在"),

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
