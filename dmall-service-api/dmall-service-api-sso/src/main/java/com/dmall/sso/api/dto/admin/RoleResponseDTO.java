package com.dmall.sso.api.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 角色列表响应实体
 * @author: created by hang.yu on 2020/1/11 22:19
 */
@Data
@ApiModel(value = "RoleResponseDTO", description = "角色列表响应实体")
public class RoleResponseDTO {

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "角色名称", position = 2)
    private String name;

    @ApiModelProperty(value = "备注", position = 3)
    private String remark;
}
