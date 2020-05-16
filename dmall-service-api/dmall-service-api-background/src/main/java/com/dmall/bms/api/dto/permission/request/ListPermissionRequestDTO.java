package com.dmall.bms.api.dto.permission.request;

import com.dmall.bms.api.enums.HttpMethodEnum;
import com.dmall.common.dto.validate.ValueInEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 权限列表请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@ApiModel(value = "ListPermissionRequestDTO", description = "权限列表请求实体")
public class ListPermissionRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务id", position = 2)
    private String appId;

    @ApiModelProperty(value = "权限名称", position = 4)
    private String name;

    @ApiModelProperty(value = "权限地址", position = 6)
    private String uri;

    @ApiModelProperty(value = "请求方式", position = 7)
    @ValueInEnum(HttpMethodEnum.class)
    private String method;

}
