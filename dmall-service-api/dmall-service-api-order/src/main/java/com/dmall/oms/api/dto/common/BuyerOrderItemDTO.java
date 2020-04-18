package com.dmall.oms.api.dto.common;

import com.dmall.oms.api.dto.aftersaledetail.AfterSaleDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: OrderItemDTO
 * @author: created by hang.yu on 2020/4/6 10:37
 */
@Data
@ApiModel(value = "BuyerOrderItemDTO", description = "sku信息")
public class BuyerOrderItemDTO {

    @ApiModelProperty(value = "orderItemId", position = 1)
    private Long orderItemId;

    @ApiModelProperty(value = "skuId", position = 2)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 3)
    private String skuName;

    @ApiModelProperty(value = "sku单价", position = 4)
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "sku数量", position = 5)
    private Integer skuNumber;

    @ApiModelProperty(value = "sku主图", position = 6)
    private String skuMainPic;

    @ApiModelProperty(value = "sku总价", position = 7)
    private BigDecimal skuTotalPrice;

    @ApiModelProperty(value = "是否可以售后", position = 8)
    private Boolean canAfterSale;

    @ApiModelProperty(value = "售后列表", position = 9)
    private List<AfterSaleDTO> afterSaleList;
}
