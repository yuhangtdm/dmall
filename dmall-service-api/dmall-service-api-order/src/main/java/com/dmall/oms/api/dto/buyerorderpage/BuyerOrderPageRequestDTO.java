package com.dmall.oms.api.dto.buyerorderpage;

import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 买家端订单分页请求实体
 * @author: created by hang.yu on 2020/4/9 22:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BuyerOrderPageRequestDTO", description = "买家端订单分页请求实体")
public class BuyerOrderPageRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "订单号", position = 1)
    private Long orderId;

    @ApiModelProperty(value = "sku名称", position = 2)
    private String skuName;

    @ApiModelProperty(value = "订单状态", position = 2)
    @ValueInEnum(OrderStatusEnum.class)
    private Integer orderStatus;
}
