package com.dmall.pms.api.dto.product.request.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 参数值实体
 * @author: created by hang.yu on 2019/12/26 22:46
 */
@Data
@ApiModel(value = "ParamValueRequestDTO", description = "参数值实体")
public class ParamValueRequestDTO implements Serializable {

    private static final long serialVersionUID = 2436674140152898894L;

    @ApiModelProperty(value = "参数属性id", required = true, position = 1)
    @NotNull(message = "参数属性id不能为空")
    private Long attributeId;

    @ApiModelProperty(value = "参数属性值列表", required = true, position = 2)
    @NotNull(message = "参数属性值列表不能为空")
    @Size(min = 1, message = "参数属性值列表不能为空")
    private List<String> paramValues;
}
