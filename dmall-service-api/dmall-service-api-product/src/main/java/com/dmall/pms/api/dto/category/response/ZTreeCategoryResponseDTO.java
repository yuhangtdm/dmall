package com.dmall.pms.api.dto.category.response;

import com.dmall.pms.api.enums.LevelEnum;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: ZTree树响应实体
 * @author: created by hang.yu on 2019/11/24 18:30
 */
@Data
@ApiModel(value = "ZTreeCategoryResponseDTO", description = "ZTree分类树实体")
public class ZTreeCategoryResponseDTO implements Serializable {

    private static final long serialVersionUID = 7853335345390628555L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "上级id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "分类名称", position = 3)
    private String name;

    @ApiModelProperty(value = "排序", position = 4)
    private Integer sort;

    @ApiModelProperty(value = "级别", position = 5)
    private LevelEnum level;

    @ApiModelProperty(value = "子元素", position = 6)
    private List<ZTreeCategoryResponseDTO> children = Lists.newArrayList();

    @ApiModelProperty(value = "是否打开", position = 7)
    private Boolean open = false;

    @ApiModelProperty(value = "是否为父元素", position = 8)
    private Boolean isParent = false;
}
