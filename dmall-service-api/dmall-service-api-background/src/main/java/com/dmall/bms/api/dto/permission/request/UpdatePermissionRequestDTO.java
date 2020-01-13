package com.dmall.bms.api.dto.permission.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.permission.common.CommonPermissionRequestDTO;

/**
 * @description: 修改资源请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdatePermissionRequestDTO", description = "修改资源请求实体")
public class UpdatePermissionRequestDTO extends CommonPermissionRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
