package com.dmall.pms.api.dto.brand.request;

import com.dmall.pms.api.dto.brand.common.BrandCommonRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

/**
 * @description: 修改品牌实体
 * @author: created by hang.yu on 2019/11/23 9:45
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UpdateBrandRequestDTO", description = "修改品牌实体")
@EqualsAndHashCode(callSuper = false)
public class UpdateBrandRequestDTO extends BrandCommonRequestDTO {

    private static final long serialVersionUID = -6653891794928685135L;

    @ApiModelProperty(value = "品牌id", required = true, position = 1)
    @NotNull(message = "品牌id不能为空")
    private Long id;
}
