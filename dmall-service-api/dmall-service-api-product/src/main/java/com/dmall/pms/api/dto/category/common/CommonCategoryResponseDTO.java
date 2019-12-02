package com.dmall.pms.api.dto.category.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 商品分类公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonCategoryResponseDTO", description="商品分类公共响应实体")
public class CommonCategoryResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", required = true, position = 1)
    private Long id;

    @ApiModelProperty(value = "上级id", required = true, position = 2)
    private Long parentId;


    @ApiModelProperty(value = "分类名称", required = true, position = 3)
    private String name;

    @ApiModelProperty(value = "PC图标",  position = 4)
    private String icon;


    @ApiModelProperty(value = "移动端图标",  position = 5)
    private String mobileIcon;


    @ApiModelProperty(value = "级别",required = true, position = 6)
    private Integer level;


    @ApiModelProperty(value = "描述",  position = 7)
    private String description;

    @ApiModelProperty(value = "排序",  position = 8)
    private Integer sort;


    @ApiModelProperty(value = "关键字,用于搜索",position = 9)
    private String keywords;


    @ApiModelProperty(value = "是否热门 Y-是;N-否",  position = 10)
    private String hotStatus;


    @ApiModelProperty(value = "是否显示在导航栏 Y-是;N-否", position = 11)
    private String navStatus;


    @ApiModelProperty(value = "品牌数量",  position = 12)
    private Integer brandCount;


    @ApiModelProperty(value = "属性分类数量",  position = 13)
    private Integer attributeCategoryCount;

    @ApiModelProperty(value = "属性数量",  position = 14)
    private Integer attributeCount;


    @ApiModelProperty(value = "路径", required = true, position = 15)
    private String path;

    @ApiModelProperty(value = "创建人",  position = 16)
    private Long creator;

    @ApiModelProperty(value = "创建人姓名",  position = 17)
    private String creatorName;

    @ApiModelProperty(value = "创建时间", position = 18)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 19)
    private Long modifier;

    @ApiModelProperty(value = "更新人姓名",  position = 20)
    private Long modifierName;

    @ApiModelProperty(value = "更新时间",  position = 21)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用",  position = 22)
    private String isDeleted;
}
