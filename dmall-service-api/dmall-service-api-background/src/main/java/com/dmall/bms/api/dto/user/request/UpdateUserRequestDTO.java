package com.dmall.bms.api.dto.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.user.common.CommonUserRequestDTO;

/**
 * @description: 修改后台用户请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateUserRequestDTO", description = "修改后台用户请求实体")
public class UpdateUserRequestDTO extends CommonUserRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
