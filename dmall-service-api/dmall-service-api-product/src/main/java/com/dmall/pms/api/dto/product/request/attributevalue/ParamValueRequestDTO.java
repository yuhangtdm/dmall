package com.dmall.pms.api.dto.product.request.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 参数值实体
 * @author: created by hang.yu on 2019/12/26 22:46
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ParamValueRequestDTO", description = "参数值实体")
public class ParamValueRequestDTO implements Serializable {

    private static final long serialVersionUID = 2436674140152898894L;

    @ApiModelProperty(value = "属性id", position = 1)
    @NotNull(message = "属性id不能为空")
    private Long attributeId;

    @ApiModelProperty(value = "属性值", position = 2)
    @NotNull(message = "属性值不能为空")
    private List<String> paramValues;
}
