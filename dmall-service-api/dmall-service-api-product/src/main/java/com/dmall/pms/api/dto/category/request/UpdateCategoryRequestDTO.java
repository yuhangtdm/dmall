package com.dmall.pms.api.dto.category.request;

import com.dmall.common.enums.base.YNEnum;
import com.dmall.component.web.validate.ValueInEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改商品分类请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UpdateCategoryRequestDTO" , description = "修改商品分类请求实体" )
public class UpdateCategoryRequestDTO implements Serializable {

    private static final long serialVersionUID = 1383979992923671693L;

    @ApiModelProperty(value = "主键" , required = true, position = 1)
    @NotNull(message = "分类id不能为空" )
    private Long id;

    @ApiModelProperty(value = "分类名称" , position = 2)
    private String name;

    @ApiModelProperty(value = "PC图标" , position = 3)
    private String icon;

    @ApiModelProperty(value = "移动端图标" , position = 4)
    private String mobileIcon;

    @ApiModelProperty(value = "描述" , position = 5)
    private String description;

    @ApiModelProperty(value = "排序" , position = 6)
    private Integer sort;

    @ApiModelProperty(value = "关键字,用于搜索" , position = 7)
    private String keywords;

    @ApiModelProperty(value = "是否热门 Y-是;N-否" , position = 8)
    @ValueInEnum(YNEnum.class)
    private String hotStatus;

    @ApiModelProperty(value = "是否显示在导航栏 Y-是;N-否" , position = 9)
    @ValueInEnum(YNEnum.class)
    private String navStatus;

}
