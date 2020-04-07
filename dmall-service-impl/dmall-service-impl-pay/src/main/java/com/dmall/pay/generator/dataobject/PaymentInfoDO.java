package com.dmall.pay.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 支付信息表
 * @author: created by hang.yu on 2020-04-02 22:25:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oms_payment_info")
public class PaymentInfoDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 支付方式 1-支付宝;2-微信;3-银联
     */
    private Integer paymentType;

    /**
     * 交易编号
     */
    private String tradeNo;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 交易内容
     */
    private String subject;

    /**
     * 支付状态 1-未支付;2-支付成功;3-支付失败
     */
    private Integer paymentStatus;

    /**
     * 回调信息
     */
    private String callbackContent;

    /**
     * 回调时间
     */
    private Date callBackTime;

    /**
     * 买家支付宝号
     */
    private String buyerId;

    /**
     * 买家支付宝账号
     */
    private String buyerAliPayNo;

    /**
     * 卖家支付宝账号
     */
    private String sellerAliPayNo;

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
     * 状态 Y,可用;N:不可用
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private String isDeleted;


}
