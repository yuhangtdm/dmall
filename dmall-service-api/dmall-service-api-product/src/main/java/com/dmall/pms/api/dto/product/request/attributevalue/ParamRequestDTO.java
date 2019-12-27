package com.dmall.pms.api.dto.product.request.attributevalue;

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
@Accessors(chain = true)
@ApiModel(value = "ParamRequestDTO", description = "参数实体")
public class ParamRequestDTO implements Serializable {

    private static final long serialVersionUID = -4423360556545433218L;

    @ApiModelProperty(value = "属性分类id", position = 1)
    @NotNull(message = "属性分类id不能为空")
    private Long attributeTypeId;

    @ApiModelProperty(value = "属性列表", position = 2)
    @NotNull(message = "属性列表不能为空")
    private List<ParamValueRequestDTO> paramAttributes;
}
