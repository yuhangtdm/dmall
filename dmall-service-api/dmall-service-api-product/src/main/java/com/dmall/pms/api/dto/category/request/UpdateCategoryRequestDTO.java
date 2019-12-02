package com.dmall.pms.api.dto.category.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.category.common.CommonCategoryRequestDTO;

import javax.validation.constraints.NotNull;

/**
 * @description: 修改商品分类请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UpdateCategoryRequestDTO", description="修改商品分类请求实体")
public class UpdateCategoryRequestDTO extends CommonCategoryRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    @NotNull(message = "分类id不能为空")
    private Long id;

}
