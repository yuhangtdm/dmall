package com.dmall.mms.api.dto.memberreceiveaddress.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.memberreceiveaddress.common.CommonMemberReceiveAddressRequestDTO;

/**
 * @description: 修改会员收货地址请求实体
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UpdateMemberReceiveAddressRequestDTO", description="修改会员收货地址请求实体")
public class UpdateMemberReceiveAddressRequestDTO extends CommonMemberReceiveAddressRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
