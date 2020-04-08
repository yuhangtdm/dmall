package com.dmall.pay.api.dto;

import com.dmall.pay.api.enums.PaymentStatusEnum;
import com.dmall.pay.api.enums.PaymentTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 支付响应实体
 * @author: created by hang.yu on 2020/4/8 21:16
 */
@Data
@ApiModel(value = "PaymentResponseDTO", description = "支付响应实体")
public class PaymentResponseDTO implements Serializable {

    private static final long serialVersionUID = -3000921270471038294L;

    @ApiModelProperty(value = "支付id", position = 1)
    private Long paymentId;

    @ApiModelProperty(value = "订单号", position = 2)
    private Long orderId;

    @ApiModelProperty(value = "支付方式", position = 3)
    private PaymentTypeEnum paymentType;

    @ApiModelProperty(value = "交易编号", position = 4)
    private String tradeNo;

    @ApiModelProperty(value = "支付金额", position = 5)
    private BigDecimal amount;

    @ApiModelProperty(value = "交易内容", position = 6)
    private String subject;

    @ApiModelProperty(value = "支付状态", position = 7)
    private PaymentStatusEnum paymentStatus;

    @ApiModelProperty(value = "回调信息", position = 8)
    private String callbackContent;

    @ApiModelProperty(value = "回调时间", position = 9)
    private Date callBackTime;

    @ApiModelProperty(value = "买家支付宝账号", position = 10)
    private String buyerAliPayNo;

    @ApiModelProperty(value = "卖家支付宝账号", position = 11)
    private String sellerAliPayNo;
}
