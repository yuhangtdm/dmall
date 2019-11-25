package com.dmall.pms.api.dto.category.request;

import com.dmall.pms.api.dto.category.common.CategoryRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @description: 修改分类实体
 * @author: created by hang.yu on 2019/11/24 14:12
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UpdateCategoryRequestDTO", description = "修改分类实体")
@EqualsAndHashCode(callSuper = false)
public class UpdateCategoryRequestDTO extends CategoryRequestDTO {

    @ApiModelProperty(value = "分类id", required = true, position = 1)
    @NotNull(message = "分类id不能为空")
    private Long id;
}
