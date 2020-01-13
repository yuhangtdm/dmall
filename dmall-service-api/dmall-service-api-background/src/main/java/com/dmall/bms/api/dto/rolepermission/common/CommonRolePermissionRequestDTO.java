package com.dmall.bms.api.dto.rolepermission.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 后台角色-资源公共请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonRolePermissionRequestDTO", description = "后台角色-资源公共请求实体")
public class CommonRolePermissionRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "角色id", position = 2)
    private Long roleId;

    @ApiModelProperty(value = "资源id", position = 3)
    private Long permissionId;







}
