package com.dmall.bms.api.dto.menu.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 菜单表 列表请求实体
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Data
@ApiModel(value = "ListMenuRequestDTO", description = "菜单列表请求实体")
public class ListMenuRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "类型 1-目录;2-菜单", position = 3)
    private Integer type;

    @ApiModelProperty(value = "地址", position = 4)
    private String url;

    @ApiModelProperty(value = "图标", position = 5)
    private String icon;

}
