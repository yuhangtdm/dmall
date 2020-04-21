package com.dmall.bms.api.dto.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @description: 设置角色请求实体
 * @author: created by hang.yu on 2020/2/20 20:47
 */
@Data
@ApiModel(value = "SetRolesRequestDTO", description = "设置角色请求实体")
public class SetRolesRequestDTO implements Serializable {

    private static final long serialVersionUID = -6907230036165786746L;

    @ApiModelProperty(value = "用户id", required = true, position = 1)
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty(value = "角色id集合", required = true, position = 2)
    @NotNull(message = "角色id集合不能为空")
    @Size(min = 1, message = "角色id集合不能为空")
    private Set<Long> roleIds;

}
