package com.dmall.bms.api.dto.userrole.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 后台用户-角色列表请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:38
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListUserRoleRequestDTO", description = "后台用户-角色列表请求实体")
public class ListUserRoleRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户id", position = 2)
    private Long userId;

    @ApiModelProperty(value = "角色id", position = 3)
    private Long roleId;


}
