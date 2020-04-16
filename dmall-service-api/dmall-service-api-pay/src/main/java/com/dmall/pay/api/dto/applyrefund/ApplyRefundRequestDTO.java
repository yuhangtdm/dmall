package com.dmall.pay.api.dto.applyrefund;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pay.api.enums.PaymentTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 申请退款实体
 * @author: created by hang.yu on 2020/4/13 22:56
 */
@Data
@ApiModel(value = "ApplyRefundRequestDTO", description = "申请退款实体")
public class ApplyRefundRequestDTO implements Serializable {

    private static final long serialVersionUID = 7045504627410259269L;

    @ApiModelProperty(value = "服务单号", position = 1)
    @NotNull(message = "服务单号不能为空")
    private Long afterSaleId;

    @ApiModelProperty(value = "订单号", position = 1)
    @NotNull(message = "订单号不能为空")
    private Long orderId;

    @ApiModelProperty(value = "订单项编号", position = 2)
    @NotNull(message = "订单项编号不能为空")
    private Long orderItemId;

    @ApiModelProperty(value = "子订单号", position = 3)
    private Long subOrderId;

    @ApiModelProperty(value = "退款金额", position = 4)
    @NotBlank(message = "退款金额不能为空")
    private BigDecimal amount;

    @ApiModelProperty(value = "支付方式", position = 5)
    @NotNull(message = "支付方式不能为空")
    @ValueInEnum(PaymentTypeEnum.class)
    private Integer paymentType;

    @ApiModelProperty(value = "退款原因", position = 6)
    @NotBlank(message = "退款原因不能为空")
    private String refundReason;

    @ApiModelProperty(value = "skuId", position = 7)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 8)
    @NotBlank(message = "sku名称不能为空")
    private String skuName;

    @ApiModelProperty(value = "sku数量", position = 9)
    @NotNull(message = "sku数量不能为空")
    private Integer skuNumber;

}
