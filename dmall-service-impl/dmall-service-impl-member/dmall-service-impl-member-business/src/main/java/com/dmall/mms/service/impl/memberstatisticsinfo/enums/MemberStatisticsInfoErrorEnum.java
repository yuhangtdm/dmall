package com.dmall.mms.service.impl.memberstatisticsinfo.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员统计信息错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Getter
@AllArgsConstructor
public enum MemberStatisticsInfoErrorEnum implements ErrorCodeEnum {

    MEMBERSTATISTICSINFO_NOT_EXIST("memberStatisticsInfo_100","该会员统计信息不存在"),

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
