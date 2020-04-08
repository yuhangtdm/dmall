package com.dmall.oms.api.dto.buyerdetail;

import com.dmall.pay.api.enums.PaymentTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 支付信息响应实体
 * @author: created by hang.yu on 2020/4/5 22:32
 */
@Data
@ApiModel(value = "PaymentDTO", description = "支付信息响应实体")
public class PaymentDTO {

    @ApiModelProperty(value = "支付方式", position = 1)
    private PaymentTypeEnum paymentType;

    @ApiModelProperty(value = "付款时间", position = 2)
    private Date paymentDate;

    @ApiModelProperty(value = "商品总金额", position = 3)
    private BigDecimal totalSkuAmount;

    @ApiModelProperty(value = "支付总金额", position = 4)
    private BigDecimal paymentAmount;

    @ApiModelProperty(value = "优惠券金额", position = 5)
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "运费金额", position = 6)
    private BigDecimal freightAmount;
}
