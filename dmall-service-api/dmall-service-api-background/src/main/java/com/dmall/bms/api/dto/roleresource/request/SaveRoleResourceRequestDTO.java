package com.dmall.bms.api.dto.roleresource.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.roleresource.common.CommonRoleResourceRequestDTO;

/**
 * @description: 新增后台角色-资源请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveRoleResourceRequestDTO", description = "新增后台角色-资源请求实体")
public class SaveRoleResourceRequestDTO extends CommonRoleResourceRequestDTO {

}
