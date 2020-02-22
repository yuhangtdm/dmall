package com.dmall.mms.api.dto.coupon.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 会员优惠券表 公共请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonCouponRequestDTO", description = "会员优惠券表 公共请求实体")
public class CommonCouponRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;

    @ApiModelProperty(value = "优惠券id", position = 3)
    private Long couponId;







}
