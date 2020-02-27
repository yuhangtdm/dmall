package com.dmall.mms.api.dto.coupon.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.coupon.common.CommonCouponRequestDTO;

/**
 * @description: 修改会员优惠券表 请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateCouponRequestDTO", description = "修改会员优惠券表 请求实体")
public class UpdateCouponRequestDTO extends CommonCouponRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
