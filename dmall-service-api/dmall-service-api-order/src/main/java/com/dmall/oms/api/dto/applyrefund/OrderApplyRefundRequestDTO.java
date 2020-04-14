package com.dmall.oms.api.dto.applyrefund;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 申请退款请求实体
 * @author: created by hang.yu on 2020/4/14 22:00
 */
@Data
@ApiModel(value = "OrderApplyRefundRequestDTO", description = "申请退款请求实体")
public class OrderApplyRefundRequestDTO {

    @ApiModelProperty(value = "订单项号", position = 1)
    @NotNull(message = "订单项号不能为空")
    private Long orderItemId;

    @ApiModelProperty(value = "退款原因", position = 2)
    @NotNull(message = "退款原因不能为空")
    private String reason;

    @ApiModelProperty(value = "退款备注", position = 3)
    private String description;
}
