package com.dmall.bms.api.dto.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改后台用户请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@ApiModel(value = "UpdateUserRequestDTO", description = "修改后台用户请求实体")
public class UpdateUserRequestDTO implements Serializable {

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

    @ApiModelProperty(value = "仓库id", position = 9)
    private Long warehouseId;

}
