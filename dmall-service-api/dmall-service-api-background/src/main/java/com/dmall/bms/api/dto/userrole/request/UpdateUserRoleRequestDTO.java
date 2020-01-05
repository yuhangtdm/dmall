package com.dmall.bms.api.dto.userrole.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.userrole.common.CommonUserRoleRequestDTO;

/**
 * @description: 修改后台用户-角色请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:38
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateUserRoleRequestDTO", description = "修改后台用户-角色请求实体")
public class UpdateUserRoleRequestDTO extends CommonUserRoleRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
