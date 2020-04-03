package com.dmall.oms.api.dto.paymentinfo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.oms.api.dto.paymentinfo.common.CommonPaymentInfoRequestDTO;

/**
 * @description: 修改支付信息请求实体
 * @author: created by hang.yu on 2020-04-02 22:25:15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdatePaymentInfoRequestDTO", description = "修改支付信息请求实体")
public class UpdatePaymentInfoRequestDTO extends CommonPaymentInfoRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
