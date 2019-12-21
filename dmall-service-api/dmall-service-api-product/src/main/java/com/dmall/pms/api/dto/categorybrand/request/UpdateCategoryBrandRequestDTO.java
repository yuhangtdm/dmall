package com.dmall.pms.api.dto.categorybrand.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.categorybrand.common.CommonCategoryBrandRequestDTO;

/**
 * @description: 修改分类品牌关系请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateCategoryBrandRequestDTO", description = "修改分类品牌关系请求实体")
public class UpdateCategoryBrandRequestDTO extends CommonCategoryBrandRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
