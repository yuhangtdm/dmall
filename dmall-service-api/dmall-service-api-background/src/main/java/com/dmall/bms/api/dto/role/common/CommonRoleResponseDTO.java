package com.dmall.bms.api.dto.role.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 后台角色公共响应实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonRoleResponseDTO", description = "后台角色公共响应实体")
public class CommonRoleResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "角色名称", position = 2)
    private String name;

    @ApiModelProperty(value = "备注", position = 3)
    private String remark;

}
