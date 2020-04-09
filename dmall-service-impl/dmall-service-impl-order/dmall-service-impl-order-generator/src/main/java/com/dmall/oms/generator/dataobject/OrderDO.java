package com.dmall.oms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 订单表
 * @author: created by hang.yu on 2020-03-12 23:12:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oms_order")
public class OrderDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单状态 1-待支付;2-待发货;3-待收货;5-已完成,6-已取消
     */
    private Integer status;

    /**
     * 订单支付状态 1-待支付;2-支付中;3-支付成功;4-支付失败
     */
    private Integer paymentStatus;

    /**
     * 发货状态: 0-未发货;1-部分发货;2-全部发货
     */
    private Integer deliverStatus;

    /**
     * 发货状态: 0-待收货;1-部分收货;2-全部收货
     */
    private Integer receiveStatus;

    /**
     * 评价状态 0-未评价;1-部分评价;2-全部评价
     */
    private Integer commentStatus;

    /**
     * 订单来源 1-PC;2-APP;3-小程序
     */
    private Integer source;

    /**
     * 支付方式 1-支付宝;2-微信;3-银联
     */
    private Integer paymentType;

    /**
     * 取消方式 1-手动取消;2-自动取消
     */
    private Integer cancelType;

    /**
     * sku总数量
     */
    private Integer skuCount;

    /**
     * 商品总数量
     */
    private Integer productCount;

    /**
     * 商品总金额
     */
    private BigDecimal totalSkuPrice;

    /**
     * 运费金额
     */
    private BigDecimal freightPrice;

    /**
     * 订单总金额
     */
    private BigDecimal orderPrice;

    /**
     * 实际支付金额 订单总金额+运费金额-促销优惠金额-积分抵扣金额-优惠券抵扣金额
     */
    private BigDecimal paymentPrice;

    /**
     * 订单实际金额(含退款)
     */
    private BigDecimal dealPrice;

    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponPrice;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 订单拆分情况: 1-未拆分;2-无需拆分;3-已拆分;
     */
    private Integer split;

    /**
     * 拆单原因
     */
    private String splitReason;

    /**
     * 拆单人员
     */
    private Long splitPerson;

    /**
     * 收货地址id
     */
    private Long receiverId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人省份/直辖市
     */
    private String receiverProvince;

    /**
     * 收货人城市
     */
    private String receiverCity;

    /**
     * 收货人区/县
     */
    private String receiverRegion;

    /**
     * 收货人详细地址
     */
    private String receiverDetailAddress;

    /**
     * 物流单号 多个用逗号隔开
     */
    private String logisticsNo;

    /**
     * 是否已开票
     */
    private String openInvoice;

    /**
     * 发票号码
     */
    private String invoiceNumber;

    /**
     * 发票类型 0-不开发票；1-电子发票；2-纸质发票 默认电子发票
     */
    private Integer invoiceType;

    /**
     * 发票抬头 1-个人;2-单位
     */
    private Integer invoiceHeader;

    /**
     * 个人名称
     */
    private String personalName;

    /**
     * 发票内容 1-商品类别;2-商品明细
     */
    private Integer invoiceContent;

    /**
     * 单位名称
     */
    private String companyName;

    /**
     * 纳税人识别号
     */
    private String customerTaxNumber;

    /**
     * 收票人电话
     */
    private String invoiceReceiverPhone;

    /**
     * 收票人邮箱
     */
    private String invoiceReceiverEmail;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 确认收货时间
     */
    private Date receiveTime;

    /**
     * 取消订单时间
     */
    private Date cancelTime;

    /**
     * 删除订单时间
     */
    private Date deleteTime;

    /**
     * 开票时间
     */
    private Date invoiceTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreated;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifier;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 状态 Y-可用;N-不可用
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private String isDeleted;

}
