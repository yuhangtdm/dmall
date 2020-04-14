package com.dmall.oms.generator.dataobject;

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
 * @description: 退换货申请表
 * @author: created by hang.yu on 2020-04-13 21:52:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oms_order_after_sale_apply")
public class OrderAfterSaleApplyDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 子订单号
     */
    private Long subOrderId;

    /**
     * 订单项id
     */
    private Long orderItemId;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 类型 1-退款;2-退货退款;3-换货
     */
    private Integer type;

    /**
     * 状态 1-已申请;2-退/换货中;3-退款中;3-退款完成;4-已拒绝;5-已关闭
     */
    private Integer status;

    /**
     * 申请原因
     */
    private String reason;

    /**
     * 申请描述
     */
    private String description;

    /**
     * 申请凭证 上传凭证照片,多张以逗号隔开
     */
    private String applyCredentials;

    /**
     * 处理人员
     */
    private Long handleMan;

    /**
     * 处理备注
     */
    private String handleNote;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 处理时间
     */
    private Date handleTime;

    /**
     * 退款时间
     */
    private Date refundTime;

    /**
     * 填写快递单号时间
     */
    private Date fillLogisticsNoTime;

    /**
     * 卖家收货时间
     */
    private Date receiveTime;

    /**
     * 拒绝时间
     */
    private Date refuseTime;

    /**
     * 关闭时间
     */
    private Date closeTime;
    /**
     * 退款方式 1-原返
     */
    private Integer refundType;

    /**
     * 买家发货人姓名
     */
    private String buyerName;

    /**
     * 买家发货人电话
     */
    private String buyerPhone;

    /**
     * 买家发货人详细地址
     */
    private String buyerDetailAddress;

    /**
     * 卖家收货人电话
     */
    private String sellerName;

    /**
     * 卖家收货人地址
     */
    private String sellerPhone;

    /**
     * 卖家收货人详细地址
     */
    private String sellerDetailAddress;

    /**
     * 物流单号
     */
    private String logisticsNo;

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
