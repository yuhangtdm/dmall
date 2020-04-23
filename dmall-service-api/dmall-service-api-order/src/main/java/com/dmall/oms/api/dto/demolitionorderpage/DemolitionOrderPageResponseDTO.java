package com.dmall.oms.api.dto.demolitionorderpage;

import com.dmall.common.enums.SourceEnum;
import com.dmall.oms.api.enums.CancelTypeEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.api.enums.SplitEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 拆单分页响应实体
 * @author: created by hang.yu on 2020/4/4 15:43
 */
@Data
@ApiModel(value = "PageOrderResponseDTO", description = "拆单分页响应实体")
public class DemolitionOrderPageResponseDTO implements Serializable {

    private static final long serialVersionUID = 7077582388689077395L;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", position = 1)
    private Long orderId;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态", position = 2)
    private OrderStatusEnum orderStatus;

    /**
     * 订单来源
     */
    @ApiModelProperty(value = "订单来源", position = 3)
    private SourceEnum source;

    /**
     * 会员id
     */
    @ApiModelProperty(value = "会员id", position = 4)
    private Long memberId;

    /**
     * 商品总数量
     */
    @ApiModelProperty(value = "商品总数量", position = 5)
    private Integer productCount;

    /**
     * sku总数量
     */
    @ApiModelProperty(value = "sku总数量", position = 6)
    private Integer skuCount;

    /**
     * 订单总金额
     */
    @ApiModelProperty(value = "订单总金额", position = 7)
    private BigDecimal orderPrice;

    /**
     * 实际支付金额 订单总金额+运费金额-促销优惠金额-积分抵扣金额-优惠券抵扣金额
     */
    @ApiModelProperty(value = "实际支付金额", position = 8)
    private BigDecimal payPrice;

    /**
     * 订单实际金额(含退款)
     */
    @ApiModelProperty(value = "订单实际金额(含退款)", position = 9)
    private BigDecimal dealPrice;

    /**
     * sku总金额
     */
    @ApiModelProperty(value = "sku总金额", position = 10)
    private BigDecimal totalSkuPrice;

    /**
     * 运费金额
     */
    @ApiModelProperty(value = "运费金额", position = 11)
    private BigDecimal freightPrice;

    /**
     * 订单是否拆分: 1-未拆分;2-无需拆分;3-已拆分;
     */
    @ApiModelProperty(value = "订单是否拆分: 1-未拆分;2-无需拆分;3-已拆分;", position = 12)
    private SplitEnum isSplit;

    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名", position = 13)
    private String receiverName;

    /**
     * 收货人电话
     */
    @ApiModelProperty(value = "收货人电话", position = 14)
    private String receiverPhone;

    /**
     * 物流单号 多个用逗号隔开
     */
    @ApiModelProperty(value = "物流单号 多个用逗号隔开", position = 15)
    private String logisticsNo;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间", position = 16)
    private Date paymentTime;

    /**
     * 确认收货时间
     */
    @ApiModelProperty(value = "确认收货时间", position = 18)
    private Date receiveTime;

    /**
     * 取消订单时间
     */
    @ApiModelProperty(value = "取消订单时间", position = 19)
    private Date cancelTime;

    /**
     * 删除订单时间
     */
    @ApiModelProperty(value = "删除订单时间", position = 20)
    private Date deleteTime;

    /**
     * 开票时间
     */
    @ApiModelProperty(value = "开票时间", position = 21)
    private Date invoiceTime;

    /**
     * 取消方式
     */
    @ApiModelProperty(value = "取消方式", position = 22)
    private CancelTypeEnum cancelType;

    /**
     * 订单创建时间
     */
    @ApiModelProperty(value = "订单创建时间", position = 23)
    private Date createTime;
}
