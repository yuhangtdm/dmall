package com.dmall.pms.api.dto.product.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 新增商品属性值实体
 * @author: created by hang.yu on 2019/12/10 22:25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AttributeValueDTO", description = "新增商品属性值实体")
public class AttributeValueDTO implements Serializable {

    private static final long serialVersionUID = 1232684781888379143L;

    @ApiModelProperty(value = "属性值", position = 1)
    @NotNull(message = "属性id不能为空")
    private String value;

    @ApiModelProperty(value = "属性值配图", position = 2)
    private String pic;
}
