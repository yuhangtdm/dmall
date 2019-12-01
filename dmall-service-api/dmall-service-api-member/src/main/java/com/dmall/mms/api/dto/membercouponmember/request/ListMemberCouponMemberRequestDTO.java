package com.dmall.mms.api.dto.membercouponmember.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 会员-优惠券列表请求实体
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListMemberCouponMemberRequestDTO", description="会员-优惠券列表请求实体")
public class ListMemberCouponMemberRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




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
