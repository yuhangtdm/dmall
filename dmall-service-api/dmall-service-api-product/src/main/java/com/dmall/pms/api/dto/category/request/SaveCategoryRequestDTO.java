package com.dmall.pms.api.dto.category.request;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.common.enums.YNEnum;
import com.dmall.pms.api.enums.LevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 新增商品分类请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@ApiModel(value = "SaveCategoryRequestDTO", description = "新增商品分类请求实体")
public class SaveCategoryRequestDTO implements Serializable {

    private static final long serialVersionUID = 5469530708208456631L;

    @ApiModelProperty(value = "上级id", required = true, position = 1)
    @NotNull(message = "上级id不能为空")
    private Long parentId;

    @ApiModelProperty(value = "分类名称", required = true, position = 2)
    @NotBlank(message = "分类名称不能为空")
    private String name;

    @ApiModelProperty(value = "级别,1-1级，2-2级，3-3级", required = true, position = 3)
    @ValueInEnum(LevelEnum.class)
    @NotNull(message = "级别不能为空")
    private Integer level;

    @ApiModelProperty(value = "PC图标", position = 4)
    private String icon;

    @ApiModelProperty(value = "移动端图标", position = 5)
    private String mobileIcon;

    @ApiModelProperty(value = "描述", position = 6)
    private String description;

    @ApiModelProperty(value = "排序", position = 7)
    private Integer sort;

    @ApiModelProperty(value = "关键字,用于搜索", position = 8)
    private String keywords;

    @ApiModelProperty(value = "是否热门 Y-是;N-否", position = 9)
    @ValueInEnum(YNEnum.class)
    private String hotStatus;

    @ApiModelProperty(value = "是否显示在导航栏 Y-是;N-否", position = 10)
    @ValueInEnum(YNEnum.class)
    private String navStatus;

}
