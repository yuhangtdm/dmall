package com.dmall.bms.api.dto.userpermission.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.userpermission.common.CommonUserPermissionRequestDTO;

/**
 * @description: 新增后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限请求实体
 * @author: created by hang.yu on 2020-01-11 19:13:17
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveUserPermissionRequestDTO", description = "新增后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限请求实体")
public class SaveUserPermissionRequestDTO extends CommonUserPermissionRequestDTO {

}
