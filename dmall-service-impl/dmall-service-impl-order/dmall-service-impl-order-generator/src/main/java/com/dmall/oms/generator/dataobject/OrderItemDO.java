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
 * @description: 订单项表
 * @author: created by hang.yu on 2020-03-12 23:12:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oms_order_item")
public class OrderItemDO implements Serializable {

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
     * 商家店铺id
     */
    private String merchantsId;

    /**
     * 物流公司id
     */
    private Long logisticsId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * skuid
     */
    private Long skuId;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 商品分类id
     */
    private Long productCategoryId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单项编号
     */
    private String orderItemNo;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * sku编号
     */
    private String skuNo;

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
     * sku数量
     */
    private Integer skuQuantity;

    /**
     * sku总价
     */
    private BigDecimal totalAmount;

    /**
     * sku规格
     */
    private String skuSpecifications;

    /**
     * 运费分摊金额
     */
    private BigDecimal freightAmount;

    /**
     * 促销优惠分摊金额
     */
    private BigDecimal promotionAmount;

    /**
     * 积分抵扣分担金额
     */
    private BigDecimal integrationAmount;

    /**
     * 优惠券抵扣分摊金额
     */
    private BigDecimal couponAmount;

    /**
     * 分摊抵扣积分
     */
    private Integer integration;

    /**
     * 订单项实际总价
     */
    private BigDecimal realAmount;

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
