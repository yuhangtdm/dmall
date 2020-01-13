package com.dmall.bms.api.dto.userrole.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.userrole.common.CommonUserRoleRequestDTO;

/**
 * @description: 新增后台用户-角色请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveUserRoleRequestDTO", description = "新增后台用户-角色请求实体")
public class SaveUserRoleRequestDTO extends CommonUserRoleRequestDTO {

}
