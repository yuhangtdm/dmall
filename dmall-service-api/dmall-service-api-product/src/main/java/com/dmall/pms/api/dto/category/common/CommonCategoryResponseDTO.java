package com.dmall.pms.api.dto.category.common;

import com.dmall.common.enums.base.YNEnum;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
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
@ApiModel(value = "CommonCategoryResponseDTO", description = "商品分类公共响应实体")
public class CommonCategoryResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", required = true, position = 1)
    private Long id;

    @ApiModelProperty(value = "上级id", required = true, position = 2)
    private Long parentId;

    @ApiModelProperty(value = "分类名称", required = true, position = 3)
    private String name;

    @ApiModelProperty(value = "PC图标", position = 4)
    private String icon;

    @ApiModelProperty(value = "移动端图标", position = 5)
    private String mobileIcon;

    @ApiModelProperty(value = "级别", required = true, position = 6)
    private LevelEnum level;

    @ApiModelProperty(value = "描述", position = 7)
    private String description;

    @ApiModelProperty(value = "排序", position = 8)
    private Integer sort;

    @ApiModelProperty(value = "关键字,用于搜索", position = 9)
    private String keywords;

    @ApiModelProperty(value = "是否热门", position = 10)
    private YNEnum hotStatus;

    @ApiModelProperty(value = "是否显示在导航栏", position = 11)
    private YNEnum navStatus;

    @ApiModelProperty(value = "创建人", position = 12)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 13)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 14)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 25)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 16)
    private String isDeleted;
}
