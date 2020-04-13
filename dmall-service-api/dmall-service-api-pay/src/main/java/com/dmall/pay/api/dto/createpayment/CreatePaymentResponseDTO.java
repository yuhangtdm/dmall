package com.dmall.pay.api.dto.createpayment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 创建支付单响应出参
 * @author: created by hang.yu on 2020/4/2 23:23
 */
@Data
@ApiModel(value = "CreatePaymentResponseDTO", description = "创建支付单响应出参")
public class CreatePaymentResponseDTO implements Serializable {

    private static final long serialVersionUID = -8691742891101392103L;

    @ApiModelProperty(value = "支付返回数据", position = 1)
    private String payResult;

    @ApiModelProperty(value = "订单号", position = 1)
    private Long orderId;

    @ApiModelProperty(value = "订单金额", position = 3)
    private BigDecimal amount;

}
