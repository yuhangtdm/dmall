package com.dmall.bms.api.dto.rolepermission.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 后台角色-资源列表请求实体
 * @author: created by hang.yu on 2020-01-11 18:47:50
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListRolePermissionRequestDTO", description = "后台角色-资源列表请求实体")
public class ListRolePermissionRequestDTO implements Serializable{

private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "角色id", position = 2)
    private Long roleId;

        @ApiModelProperty(value = "资源id", position = 3)
    private Long resourceId;







}
