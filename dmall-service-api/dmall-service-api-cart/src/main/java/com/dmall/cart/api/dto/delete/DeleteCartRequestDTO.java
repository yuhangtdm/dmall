package com.dmall.cart.api.dto.delete;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description: 删除购物车请求实体
 * @author: created by hang.yu on 2020/3/14 22:30
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "DeleteCartRequestDTO", description = "删除购物车请求实体")
public class DeleteCartRequestDTO {

    @ApiModelProperty(value = "skuIds", position = 1)
    @NotNull(message = "商品列表不能为空")
    @Size(min = 1, message = "商品列表不能为空")
    private List<Long> skuIds;
}
