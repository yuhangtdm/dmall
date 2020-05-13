package com.dmall.bms.api.dto.menu.request;

import com.dmall.bms.api.enums.MenuTargetEnum;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.common.enums.YNEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改菜单表 请求实体
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Data
@ApiModel(value = "UpdateMenuRequestDTO", description = "修改菜单请求实体")
public class UpdateMenuRequestDTO implements Serializable {

    private static final long serialVersionUID = -8254617774890771860L;

    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    @ApiModelProperty(value = "地址", position = 3)
    private String url;

    @ApiModelProperty(value = "图标", position = 4)
    private String icon;

    @ApiModelProperty(value = "排序", position = 6)
    private Integer sort;

    @ApiModelProperty(value = "默认是否打开", position = 7)
    @ValueInEnum(YNEnum.class)
    private String open;

    @ApiModelProperty(value = "菜单打开方式", position = 8)
    @ValueInEnum(MenuTargetEnum.class)
    private String target;
}
