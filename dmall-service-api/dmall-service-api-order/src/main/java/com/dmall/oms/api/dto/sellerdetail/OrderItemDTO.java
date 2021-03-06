package com.dmall.oms.api.dto.sellerdetail;

import com.dmall.oms.api.dto.aftersaledetail.AfterSaleDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 订单项响应实体
 * @author: created by hang.yu on 2020/4/6 12:06
 */
@Data
@ApiModel(value = "OrderItemDTO", description = "订单项响应实体")
public class OrderItemDTO implements Serializable {

    private static final long serialVersionUID = -8722989987772499728L;

    @ApiModelProperty(value = "orderItemId", position = 1)
    private Long orderItemId;

    @ApiModelProperty(value = "skuId", position = 2)
    private Long skuId;

    @ApiModelProperty(value = "商品id", position = 3)
    private Long productId;

    @ApiModelProperty(value = "sku名称", position = 4)
    private String skuName;

    @ApiModelProperty(value = "sku主图", position = 5)
    private String skuPic;

    @ApiModelProperty(value = "sku成交单价", position = 6)
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "sku数量", position = 7)
    private Integer skuNumber;

    @ApiModelProperty(value = "sku总价", position = 8)
    private BigDecimal skuTotalPrice;

    @ApiModelProperty(value = "sku规格", position = 9)
    private String skuSpecifications;

    @ApiModelProperty(value = "优惠券抵扣分摊金额", position = 10)
    private BigDecimal couponPrice;

    @ApiModelProperty(value = "订单项实际总价", position = 11)
    private BigDecimal realPrice;

    @ApiModelProperty(value = "售后列表", position = 12)
    private List<AfterSaleDTO> afterSaleList;
}
