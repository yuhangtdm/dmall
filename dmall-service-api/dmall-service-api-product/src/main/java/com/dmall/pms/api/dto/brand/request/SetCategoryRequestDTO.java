package com.dmall.pms.api.dto.brand.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @description: 设置分类请求实体
 * @author: created by hang.yu on 2019/12/4 22:40
 */
@Data
@ApiModel(value = "SetCategoryRequestDTO", description = "设置分类请求实体")
public class SetCategoryRequestDTO implements Serializable {
    private static final long serialVersionUID = 2020918577643316332L;

    @ApiModelProperty(value = "品牌id", required = true, position = 1)
    @NotNull(message = "品牌id不能为空")
    private Long brandId;

    @ApiModelProperty(value = "分类id列表", required = true, position = 2)
    @Valid
    @NotNull(message = "分类id列表不能为空")
    @Size(min = 1, message = "分类id列表不能为空")
    private Set<Long> categoryIds;
}
