package com.dmall.bms.api.dto.permission.response.tab;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: BusinessDTO
 * @author: created by hang.yu on 2020/5/16 20:11
 */
@Data
@ApiModel(value = "PermissionDTO", description = "权限tab业务响应实体")
public class BusinessDTO {

    @ApiModelProperty(value = "业务名称", position = 1)
    private String name;

    @ApiModelProperty(value = "权限列表", position = 2)
    private List<PermissionDTO> permissions;
}
