package com.dmall.cart.api.dto.select;

import com.dmall.common.dto.validate.ValueInEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 勾选购物车商品请求实体
 * @author: created by hang.yu on 2020/3/14 17:52
 */
@Data
@ApiModel(value = "SelectCartRequestDTO", description = "勾选购物车商品请求实体")
public class SelectCartRequestDTO implements Serializable {

    @ApiModelProperty(value = "skuIds", position = 1)
    @NotNull(message = "商品列表不能为空")
    @Size(min = 1, message = "商品列表不能为空")
    private List<Long> skuIds;

    @NotNull(message = "类型不能为空")
    @ValueInEnum(SelectTypeEnum.class)
    private Integer type;
}
