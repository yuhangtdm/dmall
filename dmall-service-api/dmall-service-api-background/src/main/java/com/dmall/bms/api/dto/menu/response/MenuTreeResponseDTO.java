package com.dmall.bms.api.dto.menu.response;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 菜单树响应实体
 * @author: created by hang.yu on 2020/2/22 15:00
 */
@Data
@ApiModel(value = "MenuTreeResponseDTO", description = "菜单树响应实体")
public class MenuTreeResponseDTO implements Serializable {

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "父id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "菜单名称", position = 3)
    private String name;

    @ApiModelProperty(value = "菜单类型", position = 4)
    private Integer type;

    @ApiModelProperty(value = "菜单地址", position = 5)
    private String url;

    @ApiModelProperty(value = "菜单地址", position = 6)
    private Integer sort;

    @ApiModelProperty(value = "子菜单", position = 7)
    private List<MenuTreeResponseDTO> child = Lists.newArrayList();

}
