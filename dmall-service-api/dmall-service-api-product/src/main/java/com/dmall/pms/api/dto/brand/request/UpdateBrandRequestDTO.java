package com.dmall.pms.api.dto.brand.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.brand.common.CommonBrandRequestDTO;

import javax.validation.constraints.NotNull;

/**
 * @description: 修改品牌请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UpdateBrandRequestDTO", description="修改品牌请求实体")
public class UpdateBrandRequestDTO extends CommonBrandRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    @NotNull(message = "品牌id不能为空")
    private Long id;

}
