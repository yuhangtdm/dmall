package com.dmall.mms.api.dto.membercouponmember.request;

import com.dmall.mms.api.dto.membercouponmember.common.CommonMemberCouponMemberRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 修改会员-优惠券请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateMemberCouponMemberRequestDTO", description = "修改会员-优惠券请求实体")
public class UpdateMemberCouponMemberRequestDTO extends CommonMemberCouponMemberRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
