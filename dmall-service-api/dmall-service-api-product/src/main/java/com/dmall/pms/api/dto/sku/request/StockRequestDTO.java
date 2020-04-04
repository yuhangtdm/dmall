package com.dmall.pms.api.dto.sku.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 库存相关请求实体
 * @author: created by hang.yu on 2020/3/28 22:58
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "StockRequestDTO", description = "库存相关请求实体")
public class StockRequestDTO implements Serializable {

    @ApiModelProperty(value = "sku", position = 5)
    @NotNull(message = "sku列表不能为空")
    @Size(min = 1, message = "sku列表不能为空")
    @Valid
    private List<SkuStockRequestDTO> sku;


}
