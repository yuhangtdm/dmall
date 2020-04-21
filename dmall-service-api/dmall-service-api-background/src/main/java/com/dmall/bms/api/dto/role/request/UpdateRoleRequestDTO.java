package com.dmall.bms.api.dto.role.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改后台角色请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@ApiModel(value = "UpdateRoleRequestDTO", description = "修改后台角色请求实体")
public class UpdateRoleRequestDTO implements Serializable {

    private static final long serialVersionUID = -6394071371731325744L;

    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "角色名称", position = 2)
    private String name;

    @ApiModelProperty(value = "备注", position = 3)
    private String remark;

}
