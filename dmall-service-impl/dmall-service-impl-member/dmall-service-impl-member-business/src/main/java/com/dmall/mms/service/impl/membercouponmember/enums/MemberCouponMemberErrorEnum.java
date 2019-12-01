package com.dmall.mms.service.impl.membercouponmember.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员-优惠券错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Getter
@AllArgsConstructor
public enum  MemberCouponMemberErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBERCOUPONMEMBER_ERROR("memberCouponMember-001","新增会员-优惠券失败"),
    UPDATE_MEMBERCOUPONMEMBER_ERROR("memberCouponMember _002","修改会员-优惠券失败"),
    DELETE_MEMBERCOUPONMEMBER_ERROR("memberCouponMember_003","删除会员-优惠券失败"),
    MEMBERCOUPONMEMBER_NOT_EXIST("memberCouponMember_004","该会员-优惠券不存在"),

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
