package com.dmall.bms.api.dto.role.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: 新增后台角色请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SaveRoleRequestDTO", description = "新增后台角色请求实体")
public class SaveRoleRequestDTO implements Serializable {

    private static final long serialVersionUID = -5038973249516465822L;

    @ApiModelProperty(value = "角色名称", position = 1)
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty(value = "备注", position = 2)
    private String remark;

}
