package com.dmall.mms.api.dto.membercouponmember.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 会员-优惠券公共请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonMemberCouponMemberRequestDTO", description = "会员-优惠券公共请求实体")
public class CommonMemberCouponMemberRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "优惠券id", position = 2)
    private String couponId;


    @ApiModelProperty(value = "会员id", position = 3)
    private String memberId;


    @ApiModelProperty(value = "使用状态 1-未使用;2-已使用;3-已过期", position = 4)
    private String useStatus;


    @ApiModelProperty(value = "获取类型 1-后台赠送;2-自己领取", position = 5)
    private String getType;


    @ApiModelProperty(value = "使用时间", position = 6)
    private Date useTime;


    @ApiModelProperty(value = "使用的订单号", position = 7)
    private String orderNo;


}
