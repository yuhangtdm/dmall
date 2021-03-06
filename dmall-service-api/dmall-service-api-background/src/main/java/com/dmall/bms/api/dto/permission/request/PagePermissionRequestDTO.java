package com.dmall.bms.api.dto.permission.request;

import com.dmall.bms.api.enums.HttpMethodEnum;
import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 权限分页请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PagePermissionRequestDTO", description = "权限分页请求实体")
public class PagePermissionRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "服务appId", position = 1)
    private String appId;

    @ApiModelProperty(value = "业务名称", position = 2)
    private String business;

    @ApiModelProperty(value = "权限名称", position = 3)
    private String name;

    @ApiModelProperty(value = "权限地址", position = 4)
    private String uri;

    @ApiModelProperty(value = "请求方式", position = 5)
    @ValueInEnum(HttpMethodEnum.class)
    private String method;

}
