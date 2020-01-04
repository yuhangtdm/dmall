package com.dmall.mms.api.dto.membercouponmember.request;

import com.dmall.mms.api.dto.membercouponmember.common.CommonMemberCouponMemberRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 新增会员-优惠券请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveMemberCouponMemberRequestDTO", description = "新增会员-优惠券请求实体")
public class SaveMemberCouponMemberRequestDTO extends CommonMemberCouponMemberRequestDTO {

}
