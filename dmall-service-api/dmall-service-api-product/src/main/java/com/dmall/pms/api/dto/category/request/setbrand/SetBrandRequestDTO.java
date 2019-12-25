package com.dmall.pms.api.dto.category.request.setbrand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 设置品牌请求实体
 * @author: created by hang.yu on 2019/12/4 22:40
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SetBrandRequestDTO", description = "设置品牌请求实体")
public class SetBrandRequestDTO implements Serializable {
    private static final long serialVersionUID = 2020918577643316332L;

    @ApiModelProperty(value = "分类id", required = true, position = 1)
    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "品牌列表", required = true, position = 2)
    @Valid
    @NotNull(message = "品牌列表不能为空")
    private List<BrandIdsDTO> brandIds;
}
