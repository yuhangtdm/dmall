package com.dmall.pms.api.dto.product.request.save;

import com.dmall.component.web.validate.ValueInEnum;
import com.dmall.pms.api.dto.attribute.enums.AttributeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/10 22:12
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AttributeDTO", description = "新增商品属性实体")
public class AttributeDTO implements Serializable {

    private static final long serialVersionUID = -5904403408219551888L;

    @ApiModelProperty(value = "属性id", position = 1)
    @NotNull(message = "属性id不能为空")
    private Long attributeId;

    @ApiModelProperty(value = "属性类型", position = 1)
    @NotNull(message = "属性类型不能为空")
    @ValueInEnum(AttributeTypeEnum.class)
    private Integer attributeType;

    @ApiModelProperty(value = "属性值数组", position = 1)
    @NotNull(message = "属性值数组不能为空")
    @Valid
    private List<AttributeValueDTO> attributeValues;

}
