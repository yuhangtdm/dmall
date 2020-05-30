package com.dmall.pms.api.dto.category.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: CanScreenResponseDTO
 * @author: created by hang.yu on 2020/5/28 23:59
 */
@Data
@ApiModel(value = "CanScreenResponseDTO", description = "是否可筛选响应实体")
public class CanScreenResponseDTO {

    @ApiModelProperty(value = "id", position = 1)
    private String id;

    @ApiModelProperty(value = "name", position = 2)
    private Integer name;

    @ApiModelProperty(value = "value", position = 3)
    private String value;
}
