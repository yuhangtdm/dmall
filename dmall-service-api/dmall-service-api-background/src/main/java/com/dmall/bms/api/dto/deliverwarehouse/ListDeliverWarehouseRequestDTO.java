package com.dmall.bms.api.dto.deliverwarehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 商家发货仓库列表请求实体
 * @author: created by hang.yu on 2020/4/5 16:39
 */
@Data
@ApiModel(value = "ListDeliverWarehouseRequestDTO", description = "商家发货仓库列表请求实体")
public class ListDeliverWarehouseRequestDTO {

    @ApiModelProperty(value = "商家店铺id", required = true, position = 1)
    @NotNull(message = "商家店铺id不能为空")
    private Long merchantsId;

}
