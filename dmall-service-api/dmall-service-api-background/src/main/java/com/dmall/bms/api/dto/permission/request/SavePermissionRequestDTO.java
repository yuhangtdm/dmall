package com.dmall.bms.api.dto.permission.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.permission.common.CommonPermissionRequestDTO;

import java.io.Serializable;

/**
 * @description: 新增权限请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SavePermissionRequestDTO", description = "新增权限请求实体")
public class SavePermissionRequestDTO implements Serializable {

    @ApiModelProperty(value = "父级id", position = 1)
    private Integer parentId;

    @ApiModelProperty(value = "权限码", position = 2)
    private String code;

    @ApiModelProperty(value = "权限名称", position = 4)
    private String name;

    @ApiModelProperty(value = "1-接口地址;2-目录;3-菜单", position = 5)
    private Integer type;

    @ApiModelProperty(value = "权限地址", position = 6)
    private String uri;

    private String method;

    @ApiModelProperty(value = "图标", position = 8)
    private String icon;
}
