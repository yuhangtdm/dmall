package com.dmall.pms.api.dto.category.common;

import com.dmall.common.enums.base.YNEnum;
import com.dmall.component.web.validate.ValueInEnum;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 分类请求公共实体
 * @author: created by hang.yu on 2019/11/24 14:13
 */
@Data
public class CategoryRequestDTO implements Serializable {

    private static final long serialVersionUID = -7434048546082919047L;

    @ApiModelProperty(value = "上级id", required = true, position = 1)
    @NotNull(message = "上级id不能为空")
    private Long parentId;

    @ApiModelProperty(value = "分类名称", required = true, position = 2)
    @NotBlank(message = "分类名称不能为空")
    private String name;


    @ApiModelProperty(value = "PC图标",  position = 3)
    private String icon;


    @ApiModelProperty(value = "移动端图标", position = 4)
    private String mobileIcon;

    @ApiModelProperty(value = "级别,1-1级，2-2级，3-3级", required = true, position = 5)
    @ValueInEnum(LevelEnum.class)
    private Integer level;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述",  position = 6)
    private String description;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", position = 7)
    private Integer sort;

    /**
     * 关键字 用于搜索
     */
    @ApiModelProperty(value = "关键字,用于搜索", position = 8)
    private String keywords;

    /**
     * 是否热门 Y-是;N-否
     */
    @ApiModelProperty(value = "是否热门",  position = 9)
    @ValueInEnum(YNEnum.class)
    private String hotStatus;

    /**
     * 是否显示在导航栏 Y-是;N-否
     */
    @ApiModelProperty(value = "是否显示在导航栏", position = 10)
    @ValueInEnum(YNEnum.class)
    private String navStatus;
}
