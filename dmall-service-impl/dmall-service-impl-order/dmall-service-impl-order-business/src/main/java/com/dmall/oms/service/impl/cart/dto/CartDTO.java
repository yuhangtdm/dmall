package com.dmall.oms.service.impl.cart.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 购物车数据
 * @author: created by hang.yu on 2020/3/12 23:58
 */
@Data
public class CartDTO {

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 是否勾选
     */
    private Boolean checked;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 更新时间
     */
    private Date gmtModified;

}
