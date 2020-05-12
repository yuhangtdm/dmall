package com.dmall.bms.api.dto.menu.request;

import com.dmall.bms.api.enums.MenuTypeEnum;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.common.enums.YNEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 新增菜单请求实体
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Data
@ApiModel(value = "SaveMenuRequestDTO", description = "新增菜单请求实体")
public class SaveMenuRequestDTO implements Serializable {

    private static final long serialVersionUID = 2297156613235833664L;

    @ApiModelProperty(value = "名称", required = true, position = 1)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "上级id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "类型 1-目录;2-菜单", required = true, position = 3)
    @NotNull(message = "类型不能为空")
    @ValueInEnum(MenuTypeEnum.class)
    private Integer type;

    @ApiModelProperty(value = "地址", position = 4)
    private String url;

    @ApiModelProperty(value = "图标", position = 5)
    private String icon;

    @ApiModelProperty(value = "排序", position = 6)
    private Integer sort;

    @ApiModelProperty(value = "默认是否打开", position = 6)
    @ValueInEnum(YNEnum.class)
    private String open;
}
