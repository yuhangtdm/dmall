package com.dmall.oms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 订单项表
 * @author: created by hang.yu on 2020-03-12 23:12:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oms_order_item")
public class OrderItemDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 子订单id
     */
    private Long subOrderId;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商家店铺id
     */
    private String merchantsId;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku主图
     */
    private String skuPic;

    /**
     * sku成交单价
     */
    private BigDecimal skuPrice;

    /**
     * sku成交数量
     */
    private Integer skuNumber;

    /**
     * sku总价
     */
    private BigDecimal skuTotalPrice;

    /**
     * sku规格
     */
    private String skuSpecifications;

    /**
     * 优惠券抵扣分摊金额
     */
    private BigDecimal couponPrice;

    /**
     * 订单项实际总价
     */
    private BigDecimal realPrice;

    /**
     * 评价状态 Y-已评价;N-未评价
     */
    private String commentStatus;
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
