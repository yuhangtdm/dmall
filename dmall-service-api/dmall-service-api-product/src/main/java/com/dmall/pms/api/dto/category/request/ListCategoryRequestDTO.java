package com.dmall.pms.api.dto.category.request;

import com.dmall.pms.api.dto.category.enums.LevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @description:
 * @author: created by hang.yu on 2019/11/24 14:18
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ListCategoryRequestDTO", description = "查询分类列表实体")
@EqualsAndHashCode(callSuper = false)
public class ListCategoryRequestDTO {

    @ApiModelProperty(value = "分类名称",  position = 1)
    private String name;

    @ApiModelProperty(value = "上级id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "级别 1-1级;2-2级;3-3级", position = 35)
    private LevelEnum level;
}
