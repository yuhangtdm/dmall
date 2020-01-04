package com.dmall.pms.api.dto.category.request.setattribute;

import com.dmall.component.web.validate.ValueInEnum;
import com.dmall.pms.api.dto.category.enums.CanScreenEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @description: 属性列表实体
 * @author: created by hang.yu on 2019/12/25 23:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AttributeIdsDTO" , description = "属性列表实体" )
public class AttributeIdsDTO {

    @ApiModelProperty(value = "属性id" , required = true, position = 1)
    @NotNull(message = "属性id不能为空" )
    private Long attributeId;

    @ApiModelProperty(value = "是否可筛选,1-不可筛选;2-单选;3-多选" , required = true, position = 2)
    @NotNull(message = "是否可筛选不能为空" )
    @ValueInEnum(CanScreenEnum.class)
    private Integer canScreen;

}
