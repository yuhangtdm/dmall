package com.dmall.bms.api.dto.permission.request;

import com.dmall.bms.api.dto.permission.enums.HttpMethodEnum;
import com.dmall.bms.api.dto.permission.enums.PermissionTypeEnum;
import com.dmall.common.dto.validate.ValueInEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 权限列表请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListPermissionRequestDTO", description = "权限列表请求实体")
public class ListPermissionRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父级id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "权限码", position = 3)
    private String code;

    @ApiModelProperty(value = "权限名称", position = 4)
    private String name;

    @ApiModelProperty(value = "权限类型:1-接口地址;2-目录;3-菜单", position = 5)
    @ValueInEnum(PermissionTypeEnum.class)
    private Integer type;

    @ApiModelProperty(value = "权限地址", position = 6)
    private String uri;

    @ApiModelProperty(value = "请求方式", position = 6)
    @ValueInEnum(HttpMethodEnum.class)
    private String method;

}
