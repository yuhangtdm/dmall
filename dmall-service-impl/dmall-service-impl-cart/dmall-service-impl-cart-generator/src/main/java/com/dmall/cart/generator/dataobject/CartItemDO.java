package com.dmall.cart.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 购物车表
 * @author: created by hang.yu on 2020-03-12 23:12:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oms_cart_item")
public class CartItemDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 数量
     */
    private Integer number;

    /**
     * sku单价
     */
    private BigDecimal skuPrice;

    /**
     * sku总价
     */
    private BigDecimal skuTotalPrice;

    /**
     * 是否有货 Y-有;N-无
     */
    @TableField("hasStock")
    private String hasStock;

    /**
     * 是否勾选 Y-勾选;N-未勾选
     */
    private String checked;

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
