package com.dmall.oms.api.dto.totrade.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description: 跳转结算页请求参数
 * @author: created by hang.yu on 2020/3/26 22:32
 */
@Data
@ApiModel(value = "ToTradeRequestDTO", description = "跳转结算页请求参数")
public class ToTradeRequestDTO {

    @ApiModelProperty(value = "tradeSku", position = 1)
    @NotNull(message = "sku信息不能为空")
    @Size(min = 1, message = "sku信息不能为空")
    private List<TradeSkuRequestDTO> tradeSku;
}
