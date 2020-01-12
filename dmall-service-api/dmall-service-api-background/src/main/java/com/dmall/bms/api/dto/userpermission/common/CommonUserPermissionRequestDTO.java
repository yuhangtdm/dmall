package com.dmall.bms.api.dto.userpermission.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限公共请求实体
 * @author: created by hang.yu on 2020-01-11 19:13:17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonUserPermissionRequestDTO", description = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限公共请求实体")
public class CommonUserPermissionRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "用户id", position = 2)
    private Long userId;

    @ApiModelProperty(value = "资源id", position = 3)
    private Long permissionId;

    @ApiModelProperty(value = "类型 +:增加的权限;-:减少的权限", position = 4)
    private String type;







}
