package com.dmall.bms.api.dto.rolepermission.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.rolepermission.common.CommonRolePermissionRequestDTO;

/**
 * @description: 修改后台角色-资源请求实体
 * @author: created by hang.yu on 2020-01-11 18:47:50
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateRolePermissionRequestDTO", description = "修改后台角色-资源请求实体")
public class UpdateRolePermissionRequestDTO extends CommonRolePermissionRequestDTO {

@ApiModelProperty(value = "主键", required = true, position = 0)
private Long id;

        }
