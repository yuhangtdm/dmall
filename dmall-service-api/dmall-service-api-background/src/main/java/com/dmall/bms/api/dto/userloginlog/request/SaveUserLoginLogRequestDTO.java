package com.dmall.bms.api.dto.userloginlog.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.userloginlog.common.CommonUserLoginLogRequestDTO;

/**
 * @description: 新增后台用户登录日志请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveUserLoginLogRequestDTO", description = "新增后台用户登录日志请求实体")
public class SaveUserLoginLogRequestDTO extends CommonUserLoginLogRequestDTO {

}
