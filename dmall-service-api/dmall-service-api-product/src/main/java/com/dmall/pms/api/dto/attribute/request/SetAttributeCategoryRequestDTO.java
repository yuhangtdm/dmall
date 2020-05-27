package com.dmall.pms.api.dto.attribute.request;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.enums.CanScreenEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @description: 属性列表实体
 * @author: created by hang.yu on 2019/12/25 23:00
 */
@Data
@ApiModel(value = "SetAttribuiteCategoryRequestDTO", description = "分类列表实体")
public class SetAttributeCategoryRequestDTO {

    @ApiModelProperty(value = "属性id", required = true, position = 1)
    @NotNull(message = "属性id不能为空")
    private Long attributeId;

    @ApiModelProperty(value = "是否可筛选,1-不可筛选;2-单选;3-多选", required = true, position = 2)
    @NotNull(message = "是否可筛选不能为空")
    @ValueInEnum(CanScreenEnum.class)
    private Integer canScreen;

    @ApiModelProperty(value = "分类id列表", required = true, position = 1)
    @NotNull(message = "分类id列表不能为空")
    @Size(min = 1, message = "分类id列表不能为空")
    private Set<Long> categoryIds;

}
