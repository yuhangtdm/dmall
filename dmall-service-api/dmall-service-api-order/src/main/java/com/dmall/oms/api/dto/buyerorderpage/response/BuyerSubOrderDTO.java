package com.dmall.oms.api.dto.buyerorderpage.response;

import com.dmall.oms.api.dto.common.BuyerOrderItemDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 买家端子订单列表响应实体
 * @author: created by hang.yu on 2020/4/9 22:26
 */
@Data
@ApiModel(value = "BuyerSubOrderDTO", description = "买家端子订单列表响应实体")
public class BuyerSubOrderDTO implements Serializable {

    private static final long serialVersionUID = -8212281787284056214L;

    @ApiModelProperty(value = "子订单id", position = 1)
    private Long subOrderId;

    @ApiModelProperty(value = "子订单状态", position = 2)
    private Integer status;

    @ApiModelProperty(value = "订单项列表", position = 3)
    private List<BuyerOrderItemDTO> skuList;
}
