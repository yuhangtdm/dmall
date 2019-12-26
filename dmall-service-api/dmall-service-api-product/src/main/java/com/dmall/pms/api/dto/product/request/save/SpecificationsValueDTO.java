package com.dmall.pms.api.dto.product.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/26 22:29
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SpecificationsValueDTO", description = "销售规格值")
public class SpecificationsValueDTO implements Serializable {

    private static final long serialVersionUID = 5158187829046741491L;

    @ApiModelProperty(value = "属性值", position = 1)
    @NotNull(message = "属性值不能为空")
    private String attributeValue;

    @ApiModelProperty(value = "属性值配图", position = 2)
    private String pic;

}
