package com.dmall.bms.api.dto.roleresource.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.roleresource.common.CommonRoleResourceRequestDTO;

/**
 * @description: 修改后台角色-资源请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateRoleResourceRequestDTO", description = "修改后台角色-资源请求实体")
public class UpdateRoleResourceRequestDTO extends CommonRoleResourceRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
