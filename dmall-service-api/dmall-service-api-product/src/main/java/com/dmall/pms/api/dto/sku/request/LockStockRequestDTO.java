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
 * @description: 锁定库存请求实体
 * @author: created by hang.yu on 2020/3/28 22:58
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "LockStockRequestDTO", description = "锁定库存请求实体")
public class LockStockRequestDTO implements Serializable {

    @ApiModelProperty(value = "sku", position = 5)
    @NotNull(message = "sku列表不能为空")
    @Size(min = 1, message = "sku列表不能为空")
    @Valid
    private List<LockSkuRequestDTO> sku;


}
