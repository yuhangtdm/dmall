package com.dmall.oms.api.dto.myaftersalepage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 我的售后单分页响应实体
 * @author: created by hang.yu on 2020/4/16 22:44
 */
@Data
@ApiModel(value = "MyAfterSalePageResponseDTO", description = "我的售后单分页响应实体")
public class MyAfterSalePageResponseDTO implements Serializable {

    private static final long serialVersionUID = -3288320865097059188L;
    
    @ApiModelProperty(value = "售后单号", position = 1)
    private Long afterSaleId;

    @ApiModelProperty(value = "订单号", position = 2)
    private Long orderId;

    @ApiModelProperty(value = "skuId", position = 3)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 4)
    private String skuName;

    @ApiModelProperty(value = "申请时间", position = 5)
    private Date applyTime;

    @ApiModelProperty(value = "售后状态", position = 7)
    private String status;
}
