package com.dmall.bms.api.dto.user.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.user.common.CommonUserRequestDTO;

/**
 * @description: 新增后台用户请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveUserRequestDTO", description = "新增后台用户请求实体")
public class SaveUserRequestDTO extends CommonUserRequestDTO {

}
