package com.dmall.pms.api.dto.product.request.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: 销售规格值
 * @author: created by hang.yu on 2019/12/26 22:29
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SpecificationsValueRequestDTO" , description = "销售规格值" )
public class SpecificationsValueRequestDTO implements Serializable {

    private static final long serialVersionUID = 5158187829046741491L;

    @ApiModelProperty(value = "销售规格值" , required = true, position = 1)
    @NotBlank(message = "销售规格值不能为空" )
    private String attributeValue;

    @ApiModelProperty(value = "销售规格值配图" , position = 2)
    private String pic;

}
