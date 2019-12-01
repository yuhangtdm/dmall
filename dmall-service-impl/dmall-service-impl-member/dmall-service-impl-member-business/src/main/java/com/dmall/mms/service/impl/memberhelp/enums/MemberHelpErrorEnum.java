package com.dmall.mms.service.impl.memberhelp.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员-帮助关系表 帮助对会员有用错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Getter
@AllArgsConstructor
public enum  MemberHelpErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBERHELP_ERROR("memberHelp-001","新增会员-帮助关系表 帮助对会员有用失败"),
    UPDATE_MEMBERHELP_ERROR("memberHelp _002","修改会员-帮助关系表 帮助对会员有用失败"),
    DELETE_MEMBERHELP_ERROR("memberHelp_003","删除会员-帮助关系表 帮助对会员有用失败"),
    MEMBERHELP_NOT_EXIST("memberHelp_004","该会员-帮助关系表 帮助对会员有用不存在"),

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
