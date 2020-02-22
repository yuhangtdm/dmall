package com.dmall.mms.service.impl.memberloginlog.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员登录记录错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Getter
@AllArgsConstructor
public enum MemberLoginLogErrorEnum implements ErrorCodeEnum {

    MEMBERLOGINLOG_NOT_EXIST("memberLoginLog_100","该会员登录记录不存在"),

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
