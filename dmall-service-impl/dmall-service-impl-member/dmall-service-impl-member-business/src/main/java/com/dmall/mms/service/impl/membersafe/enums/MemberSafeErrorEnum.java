package com.dmall.mms.service.impl.membersafe.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 账户安全错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Getter
@AllArgsConstructor
public enum  MemberSafeErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBERSAFE_ERROR("memberSafe-001","新增账户安全失败"),
    UPDATE_MEMBERSAFE_ERROR("memberSafe _002","修改账户安全失败"),
    DELETE_MEMBERSAFE_ERROR("memberSafe_003","删除账户安全失败"),
    MEMBERSAFE_NOT_EXIST("memberSafe_004","该账户安全不存在"),

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
