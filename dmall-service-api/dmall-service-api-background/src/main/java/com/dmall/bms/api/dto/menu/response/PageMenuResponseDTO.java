package com.dmall.bms.api.dto.menu.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 菜单分页响应实体
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PageMenuResponseDTO", description = "菜单分页响应实体")
public class PageMenuResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "名称", position = 1)
    private String name;

    @ApiModelProperty(value = "上级id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "类型 1-目录;2-菜单", position = 3)
    private Integer type;

    @ApiModelProperty(value = "地址", position = 4)
    private String url;

    @ApiModelProperty(value = "图标", position = 5)
    private String icon;

    @ApiModelProperty(value = "创建人", position = 6)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 7)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 8)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 9)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 10)
    private String isDeleted;


}
