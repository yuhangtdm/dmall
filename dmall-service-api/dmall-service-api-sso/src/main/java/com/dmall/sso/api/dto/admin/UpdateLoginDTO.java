package com.dmall.sso.api.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 修改后台用户登录实体
 * @author: created by hang.yu on 2020/5/17 15:46
 */
@Data
@ApiModel(value = "UpdateUserRequestDTO", description = "修改后台用户登录实体")
public class UpdateLoginDTO {

    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "昵称", position = 2)
    private String nickName;

    @ApiModelProperty(value = "手机号", position = 3)
    private String phone;

    @ApiModelProperty(value = "真实姓名", position = 4)
    private String realName;

    @ApiModelProperty(value = "头像", position = 5)
    private String icon;

    @ApiModelProperty(value = "备注", position = 6)
    private String remark;

    @ApiModelProperty(value = "仓库id", position = 7)
    private Long warehouseId;

    @ApiModelProperty(value = "真实姓名", position = 4)
    private String email;
}
