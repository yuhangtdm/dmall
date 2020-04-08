package com.dmall.oms.api.dto.sellerdetail;

import com.dmall.pay.api.enums.PaymentStatusEnum;
import com.dmall.pay.api.enums.PaymentTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 订单支付信息
 * @author: created by hang.yu on 2020/4/7 23:11
 */
@Data
@ApiModel(value = "PaymentDTO", description = "订单支付信息")
public class PaymentDTO {

    @ApiModelProperty(value = "paymentId", position = 1)
    private Long paymentId;

    @ApiModelProperty(value = "订单id", position = 2)
    private Long orderId;

    @ApiModelProperty(value = "支付方式", position = 3)
    private PaymentTypeEnum paymentType;

    @ApiModelProperty(value = "交易编号", position = 5)
    private String tradeNo;

    @ApiModelProperty(value = "支付金额", position = 6)
    private BigDecimal amount;

    @ApiModelProperty(value = "交易内容", position = 7)
    private String subject;

    @ApiModelProperty(value = "支付状态", position = 8)
    private PaymentStatusEnum paymentStatus;

    @ApiModelProperty(value = "回调信息", position = 9)
    private String callbackContent;

    @ApiModelProperty(value = "回调时间", position = 10)
    private Date callBackTime;

    @ApiModelProperty(value = "买家支付宝账号", position = 11)
    private String buyerAliPayNo;

    @ApiModelProperty(value = "卖家支付宝账号", position = 12)
    private String sellerAliPayNo;

}
