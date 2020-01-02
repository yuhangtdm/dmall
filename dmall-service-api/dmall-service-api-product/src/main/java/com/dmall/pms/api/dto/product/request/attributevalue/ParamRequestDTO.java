package com.dmall.pms.api.dto.product.request.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @ApiModelProperty(value = "参数属性分类id", required = true, position = 1)
    @NotNull(message = "参数属性分类id不能为空")
    private Long attributeTypeId;

    @ApiModelProperty(value = "参数属性列表", required = true, position = 2)
    @Valid
    @NotNull(message = "参数属性列表不能为空")
    @Size(min = 1, message = "参数属性列表不能为空")
    private List<ParamValueRequestDTO> paramAttributes;
}
