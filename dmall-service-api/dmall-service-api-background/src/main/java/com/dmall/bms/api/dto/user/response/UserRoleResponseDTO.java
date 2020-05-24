package com.dmall.bms.api.dto.user.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 用户角色响应实体
 * @author: created by hang.yu on 2020/5/16 20:55
 */
@Data
@ApiModel(value = "UserResponseDTO", description = "用户角色响应实体")
public class UserRoleResponseDTO {

    @ApiModelProperty(value = "roleId", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    @ApiModelProperty(value = "角色名称", position = 2)
    private String roleName;

    @ApiModelProperty(value = "是否选中", position = 1)
    private Boolean checked;
}
