package com.dmall.bms.api.dto.role.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.role.common.CommonRoleRequestDTO;

/**
 * @description: 新增后台角色请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveRoleRequestDTO", description = "新增后台角色请求实体")
public class SaveRoleRequestDTO extends CommonRoleRequestDTO {

}
