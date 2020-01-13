package com.dmall.bms.api.dto.userpermission.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限公共响应实体
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonUserPermissionResponseDTO", description = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限公共响应实体")
public class CommonUserPermissionResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "用户id", position = 2)
    private Long userId;

    @ApiModelProperty(value = "资源id", position = 3)
    private Long permissionId;

    @ApiModelProperty(value = "类型 +:增加的权限;-:减少的权限", position = 4)
    private String type;

    @ApiModelProperty(value = "创建人", position = 5)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 6)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 7)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 8)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 9)
    private String isDeleted;


}
