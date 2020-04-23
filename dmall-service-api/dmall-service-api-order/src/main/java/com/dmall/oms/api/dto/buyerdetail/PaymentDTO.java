package com.dmall.oms.api.dto.buyerdetail;

import com.dmall.pay.api.enums.PaymentTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 支付信息响应实体
 * @author: created by hang.yu on 2020/4/5 22:32
 */
@Data
@ApiModel(value = "PaymentDTO", description = "支付信息响应实体")
public class PaymentDTO implements Serializable {

    private static final long serialVersionUID = -6608065966835180364L;

    @ApiModelProperty(value = "支付方式", position = 1)
    private PaymentTypeEnum paymentType;

    @ApiModelProperty(value = "付款时间", position = 2)
    private Date paymentDate;

    @ApiModelProperty(value = "商品总金额", position = 3)
    private BigDecimal totalSkuPrice;

    @ApiModelProperty(value = "支付总金额", position = 4)
    private BigDecimal paymentPrice;

    @ApiModelProperty(value = "优惠券金额", position = 5)
    private BigDecimal couponPrice;

    @ApiModelProperty(value = "运费金额", position = 6)
    private BigDecimal freightPrice;
}
