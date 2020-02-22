package com.dmall.mms.service.impl.memberhelp.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员-帮助关系表 帮助对会员有用错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Getter
@AllArgsConstructor
public enum MemberHelpErrorEnum implements ErrorCodeEnum {

    MEMBERHELP_NOT_EXIST("memberHelp_100","该会员-帮助关系表 帮助对会员有用不存在"),

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
