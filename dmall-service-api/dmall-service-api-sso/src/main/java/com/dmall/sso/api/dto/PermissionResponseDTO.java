package com.dmall.sso.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: 权限列表响应实体
 * @author: created by hang.yu on 2020/1/11 22:19
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PermissionResponseDTO", description = "权限列表响应实体")
public class PermissionResponseDTO {

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "资源码", position = 2)
    private String code;

    @ApiModelProperty(value = "资源名称", position = 3)
    private String name;

    @ApiModelProperty(value = "资源uri", position = 4)
    private String uri;

    @ApiModelProperty(value = "请求方式", position = 5)
    private String method;

}
