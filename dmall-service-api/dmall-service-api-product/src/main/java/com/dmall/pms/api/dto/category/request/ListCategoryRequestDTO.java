package com.dmall.pms.api.dto.category.request;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.enums.LevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 商品分类列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@ApiModel(value = "ListCategoryRequestDTO", description = "商品分类列表请求实体")
public class ListCategoryRequestDTO implements Serializable {

    private static final long serialVersionUID = -8793905767538242175L;

    @ApiModelProperty(value = "分类名称", position = 1)
    private String name;

    @ApiModelProperty(value = "上级id", required = true, position = 2)
    @NotNull(message = "上级id不能为空")
    private Long parentId;

    @ApiModelProperty(value = "级别 1-1级;2-2级;3-3级", position = 35)
    @ValueInEnum(LevelEnum.class)
    private Integer level;

}
