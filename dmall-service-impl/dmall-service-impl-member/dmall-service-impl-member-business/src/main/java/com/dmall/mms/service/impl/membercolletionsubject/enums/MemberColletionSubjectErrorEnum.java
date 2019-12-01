package com.dmall.mms.service.impl.membercolletionsubject.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员收藏专题表 错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Getter
@AllArgsConstructor
public enum  MemberColletionSubjectErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBERCOLLETIONSUBJECT_ERROR("memberColletionSubject-001","新增会员收藏专题表 失败"),
    UPDATE_MEMBERCOLLETIONSUBJECT_ERROR("memberColletionSubject _002","修改会员收藏专题表 失败"),
    DELETE_MEMBERCOLLETIONSUBJECT_ERROR("memberColletionSubject_003","删除会员收藏专题表 失败"),
    MEMBERCOLLETIONSUBJECT_NOT_EXIST("memberColletionSubject_004","该会员收藏专题表 不存在"),

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
