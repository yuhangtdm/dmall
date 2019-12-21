package com.dmall.pms.api.dto.productattributevalue.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.productattributevalue.common.CommonProductAttributeValueRequestDTO;

/**
 * @description: 修改属性值请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateProductAttributeValueRequestDTO", description = "修改属性值请求实体")
public class UpdateProductAttributeValueRequestDTO extends CommonProductAttributeValueRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
