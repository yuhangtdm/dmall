package com.dmall.pms.api.dto.product.response.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 参数实体
 * @author: created by hang.yu on 2019/12/26 22:40
 */
@Data
@ApiModel(value = "ParamResponseDTO", description = "参数响应实体")
public class ParamResponseDTO implements Serializable {

    private static final long serialVersionUID = -4423360556545433218L;

    @ApiModelProperty(value = "属性类别id", position = 2)
    @NotNull(message = "属性类别id不能为空")
    private Long attributeTypeId;

    @ApiModelProperty(value = "属性值列表", position = 2)
    @NotNull(message = "属性值列表不能为空")
    private List<ParamValueResponseDTO> params;
}
