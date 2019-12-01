package com.dmall.mms.service.impl.memberstatisticsinfo.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员统计信息错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Getter
@AllArgsConstructor
public enum  MemberStatisticsInfoErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBERSTATISTICSINFO_ERROR("memberStatisticsInfo-001","新增会员统计信息失败"),
    UPDATE_MEMBERSTATISTICSINFO_ERROR("memberStatisticsInfo _002","修改会员统计信息失败"),
    DELETE_MEMBERSTATISTICSINFO_ERROR("memberStatisticsInfo_003","删除会员统计信息失败"),
    MEMBERSTATISTICSINFO_NOT_EXIST("memberStatisticsInfo_004","该会员统计信息不存在"),

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
