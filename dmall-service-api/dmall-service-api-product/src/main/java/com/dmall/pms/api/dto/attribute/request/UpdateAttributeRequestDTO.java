package com.dmall.pms.api.dto.attribute.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.attribute.common.CommonAttributeRequestDTO;

/**
 * @description: 修改属性请求实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UpdateAttributeRequestDTO", description="修改属性请求实体")
public class UpdateAttributeRequestDTO extends CommonAttributeRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
