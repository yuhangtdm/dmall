package com.dmall.oms.api.dto.sellerdetail;

import com.dmall.common.enums.SourceEnum;
import com.dmall.common.enums.YNEnum;
import com.dmall.oms.api.enums.*;
import com.dmall.pay.api.enums.PaymentStatusEnum;
import com.dmall.pay.api.enums.PaymentTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 订单基础信息
 * @author: created by hang.yu on 2020/4/6 11:26
 */
@Data
@ApiModel(value = "OrderBasicDTO", description = "订单基础信息")
public class OrderBasicDTO {

    @ApiModelProperty(value = "订单号", position = 1)
    private Long orderId;

    @ApiModelProperty(value = "订单状态", position = 2)
    private OrderStatusEnum status;

    @ApiModelProperty(value = "订单支付状态", position = 3)
    private PaymentStatusEnum paymentStatus;

    @ApiModelProperty(value = "发货状态", position = 4)
    private OrderDeliverStatusEnum deliverStatus;

    @ApiModelProperty(value = "收货状态", position = 4)
    private OrderReceiveStatusEnum receiveStatus;

    @ApiModelProperty(value = "评价状态", position = 4)
    private OrderCommentStatusEnum commentStatus;

    @ApiModelProperty(value = "是否已删除", position = 4)
    private YNEnum deleteStatus;

    @ApiModelProperty(value = "订单来源", position = 5)
    private SourceEnum source;

    @ApiModelProperty(value = "支付方式", position = 6)
    private PaymentTypeEnum paymentType;

    @ApiModelProperty(value = "取消方式", position = 7)
    private CancelTypeEnum cancelType;

    @ApiModelProperty(value = "sku总数量", position = 8)
    private Integer skuCount;

    @ApiModelProperty(value = "商品总数量", position = 9)
    private Integer productCount;

    @ApiModelProperty(value = "商品总金额", position = 10)
    private BigDecimal totalSkuPrice;

    @ApiModelProperty(value = "运费金额", position = 11)
    private BigDecimal freightPrice;

    @ApiModelProperty(value = "订单总金额", position = 12)
    private BigDecimal orderPrice;

    @ApiModelProperty(value = "实际支付金额", position = 13)
    private BigDecimal paymentPrice;

    @ApiModelProperty(value = "订单实际金额(含退款)", position = 14)
    private BigDecimal dealPrice;

    @ApiModelProperty(value = "优惠券抵扣金额", position = 15)
    private BigDecimal couponPrice;

    @ApiModelProperty(value = "订单备注", position = 16)
    private String remark;

    @ApiModelProperty(value = "支付时间", position = 17)
    private Date paymentTime;

    @ApiModelProperty(value = "确认收货时间", position = 18)
    private Date receiveTime;

    @ApiModelProperty(value = "取消订单时间", position = 19)
    private Date cancelTime;

    @ApiModelProperty(value = "删除订单时间", position = 20)
    private Date deleteTime;

    @ApiModelProperty(value = "开票时间", position = 21)
    private Date invoiceTime;
}
