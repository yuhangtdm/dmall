package com.dmall.bms.api.dto.rolepermission.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.rolepermission.common.CommonRolePermissionRequestDTO;

/**
 * @description: 新增后台角色-资源请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveRolePermissionRequestDTO", description = "新增后台角色-资源请求实体")
public class SaveRolePermissionRequestDTO extends CommonRolePermissionRequestDTO {

}
