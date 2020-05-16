package com.dmall.bms.api.dto.permission.response.tab;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 权限tab响应实体
 * @author: created by hang.yu on 2020/5/16 14:26
 */
@Data
@ApiModel(value = "TabPermissionResponseDTO", description = "权限tab响应实体")
public class TabPermissionResponseDTO {

    @ApiModelProperty(value = "appId", position = 1)
    private String appId;

    @ApiModelProperty(value = "服务名称", position = 2)
    private String appName;

    @ApiModelProperty(value = "权限列表", position = 3)
    private List<PermissionDTO> permissions;
}
