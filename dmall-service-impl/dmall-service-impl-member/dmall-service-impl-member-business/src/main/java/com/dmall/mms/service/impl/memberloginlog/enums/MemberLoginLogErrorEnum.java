package com.dmall.mms.service.impl.memberloginlog.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员登录记录错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Getter
@AllArgsConstructor
public enum  MemberLoginLogErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBERLOGINLOG_ERROR("memberLoginLog-001","新增会员登录记录失败"),
    UPDATE_MEMBERLOGINLOG_ERROR("memberLoginLog _002","修改会员登录记录失败"),
    DELETE_MEMBERLOGINLOG_ERROR("memberLoginLog_003","删除会员登录记录失败"),
    MEMBERLOGINLOG_NOT_EXIST("memberLoginLog_004","该会员登录记录不存在"),

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
