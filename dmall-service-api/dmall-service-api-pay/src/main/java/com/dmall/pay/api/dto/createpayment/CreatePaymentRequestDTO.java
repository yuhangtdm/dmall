package com.dmall.pay.api.dto.createpayment;

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
 * @description: 支付请求入参
 * @author: created by hang.yu on 2020/4/2 23:23
 */
@Data
@ApiModel(value = "CreatePaymentRequestDTO", description = "支付请求入参")
public class CreatePaymentRequestDTO implements Serializable {

    private static final long serialVersionUID = 8089554331548887891L;

    @ApiModelProperty(value = "订单号", position = 1)
    @NotNull(message = "订单号不能为空")
    private Long orderId;

    @ApiModelProperty(value = "订单标题", position = 2)
    @NotBlank(message = "订单标题不能为空")
    private String subject;

    @ApiModelProperty(value = "订单金额", position = 3)
    @NotNull(message = "订单金额不能为空")
    private BigDecimal amount;

    @ApiModelProperty(value = "支付方式", position = 4)
    @NotNull(message = "支付方式不能为空")
    @ValueInEnum(PaymentTypeEnum.class)
    private Integer paymentType;

}
