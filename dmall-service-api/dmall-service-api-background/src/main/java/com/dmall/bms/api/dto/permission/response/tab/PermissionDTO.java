package com.dmall.bms.api.dto.permission.response.tab;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: PermissionDTO
 * @author: created by hang.yu on 2020/5/16 14:27
 */
@Data
@ApiModel(value = "PermissionDTO", description = "权限tab数据响应实体")
public class PermissionDTO {

    @ApiModelProperty(value = "id", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "权限名称", position = 2)
    private String name;

    @ApiModelProperty(value = "是否选中", position = 3)
    private Boolean checked;
}
