package com.dmall.pms.api.dto.category.request;

import com.dmall.common.enums.base.YNEnum;
import com.dmall.component.web.validate.ValueInEnum;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.category.common.CommonCategoryRequestDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 修改商品分类请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value="UpdateCategoryRequestDTO", description="修改商品分类请求实体")
public class UpdateCategoryRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotNull(message = "分类id不能为空")
    private Long id;

    @ApiModelProperty(value = "分类名称", required = true, position = 2)
    private String name;

    @ApiModelProperty(value = "PC图标",  position = 3)
    private String icon;

    @ApiModelProperty(value = "移动端图标", position = 4)
    private String mobileIcon;

    @ApiModelProperty(value = "描述",  position = 6)
    private String description;

    @ApiModelProperty(value = "排序", position = 7)
    private Integer sort;

    @ApiModelProperty(value = "关键字,用于搜索", position = 8)
    private String keywords;

    @ApiModelProperty(value = "是否热门 Y-是;N-否",  position = 9)
    @ValueInEnum(YNEnum.class)
    private String hotStatus;

    @ApiModelProperty(value = "是否显示在导航栏 Y-是;N-否", position = 10)
    @ValueInEnum(YNEnum.class)
    private String navStatus;

}
