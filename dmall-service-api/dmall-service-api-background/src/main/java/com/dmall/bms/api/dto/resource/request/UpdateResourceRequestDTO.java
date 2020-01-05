package com.dmall.bms.api.dto.resource.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.resource.common.CommonResourceRequestDTO;

/**
 * @description: 修改资源请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateResourceRequestDTO", description = "修改资源请求实体")
public class UpdateResourceRequestDTO extends CommonResourceRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
