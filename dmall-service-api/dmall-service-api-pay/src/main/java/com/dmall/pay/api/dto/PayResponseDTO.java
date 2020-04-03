package com.dmall.pay.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 支付返回出参
 * @author: created by hang.yu on 2020/4/2 23:23
 */
@Data
@ApiModel(value = "PayResponseDTO", description = "支付请求出参")
public class PayResponseDTO {

    @ApiModelProperty(value = "支付返回数据", position = 1)
    private String aliPayResult;

    @ApiModelProperty(value = "订单号", position = 1)
    private Long orderId;

    @ApiModelProperty(value = "订单金额", position = 3)
    private BigDecimal amount;

}
