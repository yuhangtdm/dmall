package com.dmall.bms.api.dto.permission.common;

import com.dmall.bms.api.dto.permission.enums.PermissionTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 权限公共响应实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonPermissionResponseDTO", description = "权限公共响应实体")
public class CommonPermissionResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "父级id", position = 2)
    private Integer parentId;

    @ApiModelProperty(value = "权限码", position = 3)
    private String code;

    @ApiModelProperty(value = "权限名称", position = 4)
    private String name;

    @ApiModelProperty(value = "权限类型", position = 5)
    private PermissionTypeEnum type;

    @ApiModelProperty(value = "权限地址", position = 6)
    private String uri;

    @ApiModelProperty(value = "请求方式", position = 6)
    private String method;

    @ApiModelProperty(value = "图标", position = 8)
    private String icon;

}
