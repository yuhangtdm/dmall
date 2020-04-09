package com.dmall.pms.api.dto.sku.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 校验创建订单请求实体
 * @author: created by hang.yu on 2020/3/28 15:48
 */
@Data
@ApiModel(value = "CheckCreateOrderRequestDTO", description = "校验创建订单请求实体")
public class CheckCreateOrderRequestDTO {

    @ApiModelProperty(value = "sku信息", position = 1)
    @NotNull(message = "sku信息不能为空")
    @Size(min = 1, message = "sku信息不能为空")
    private List<CheckOrderSkuRequestDTO> orderSku;

    @ApiModelProperty(value = "sku总价", position = 4)
    @NotNull(message = "sku总价不能为空")
    private BigDecimal totalSkuPrice;

    @ApiModelProperty(value = "运费", position = 5)
    @NotNull(message = "运费不能为空")
    private BigDecimal freightPrice;

    @ApiModelProperty(value = "订单总价", position = 6)
    @NotNull(message = "订单总价不能为空")
    private BigDecimal orderPrice;

}
