package com.dmall.mms.api.dto.membercouponmember.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.membercouponmember.common.CommonMemberCouponMemberRequestDTO;

/**
 * @description: 新增会员-优惠券请求实体
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveMemberCouponMemberRequestDTO", description="新增会员-优惠券请求实体")
public class SaveMemberCouponMemberRequestDTO extends CommonMemberCouponMemberRequestDTO {

}
