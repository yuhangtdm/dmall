package com.dmall.pms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 商品分类表
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CategoryDTO", description = "商品分类表")
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id 主键", position = 1)
    private Long id;

    @ApiModelProperty(value = "父级id 上级分类编号", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "分类名称", position = 3)
    private String name;

    @ApiModelProperty(value = "PC图标", position = 4)
    private String icon;

    @ApiModelProperty(value = "移动端图标", position = 5)
    private String mobileIcon;

    @ApiModelProperty(value = "级别 1-1级;2-2级;3-3级", position = 6)
    private Integer level;

    @ApiModelProperty(value = "描述", position = 7)
    private String description;

    @ApiModelProperty(value = "排序", position = 8)
    private Integer sort;

    @ApiModelProperty(value = "关键字 用于搜索", position = 9)
    private String keywords;

    @ApiModelProperty(value = "是否热门 Y-是;N-否", position = 10)
    private String hotStatus;

    @ApiModelProperty(value = "是否显示在导航栏 Y-是;N-否", position = 11)
    private String navStatus;

    @ApiModelProperty(value = "路径 格式: .parentId.id.", position = 12)
    private String path;

    @ApiModelProperty(value = "创建人", position = 13)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 14)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 15)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 16)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 17)
    private String isDeleted;

}
