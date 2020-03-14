package com.dmall.oms.generator.dataobject;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
     * 会员id
     */
    private Long memberId;

    /**
     * 物流公司id
     */
    private Long logisticsId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单状态 1-待支付;2-待发货;3-待收货;5-已完成,6-已取消
     */
    private Integer status;

    /**
     * 订单来源 1-PC;2-APP;3-小程序
     */
    private Integer source;

    /**
     * 支付方式 1-支付宝;2-微信;3-银联
     */
    private Integer payType;

    /**
     * 会员手机号
     */
    private String memberPhone;

    /**
     * 商品总数量
     */
    private Integer productCount;

    /**
     * 订单总金额 下单商品的金额,不含运费
     */
    private BigDecimal totalAmount;

    /**
     * vip订单金额
     */
    private BigDecimal vipTotalAmount;

    /**
     * vip节省金额
     */
    private BigDecimal vipSaveAmount;

    /**
     * 实际支付金额 订单总金额+运费金额-促销优惠金额-积分抵扣金额-优惠券抵扣金额
     */
    private BigDecimal payAmount;

    /**
     * 运费金额
     */
    private BigDecimal freightAmount;

    /**
     * 促销优惠金额 促销价、满减、阶梯价等 1期暂时不做
     */
    private BigDecimal promotionAmount;

    /**
     * 积分抵扣金额 1期暂时不做
     */
    private BigDecimal integrationAmount;

    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponAmount;

    /**
     * 抵扣积分 1期暂时不做
     */
    private BigDecimal integration;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    private String receiverPostCode;

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
     * 物流单号
     */
    private String logisticsNo;

    /**
     * 发货人姓名
     */
    private String deliveryName;

    /**
     * 发货人电话
     */
    private String deliveryPhone;

    /**
     * 发货省份/直辖市
     */
    private String deliveryProvince;

    /**
     * 发货城市
     */
    private String deliveryCity;

    /**
     * 发货人区/县
     */
    private String deliveryRegion;

    /**
     * 发货详细地址
     */
    private String deliveryDetailAddress;

    /**
     * 发票类型 0-不开发票；1-电子发票；2-纸质发票
     */
    private Integer billType;

    /**
     * 发票抬头
     */
    private String billHeader;

    /**
     * 发票内容
     */
    private String billContent;

    /**
     * 收票人姓名
     */
    private String billReceiverName;

    /**
     * 收票人电话
     */
    private String billReceiverPhone;

    /**
     * 收票人邮箱
     */
    private String billReceiverEmail;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;

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
