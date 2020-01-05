package com.dmall.bms.api.dto.userrole.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 后台用户-角色公共请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:38
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonUserRoleRequestDTO", description = "后台用户-角色公共请求实体")
public class CommonUserRoleRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户id", position = 2)
    private Long userId;

    @ApiModelProperty(value = "角色id", position = 3)
    private Long roleId;


}
