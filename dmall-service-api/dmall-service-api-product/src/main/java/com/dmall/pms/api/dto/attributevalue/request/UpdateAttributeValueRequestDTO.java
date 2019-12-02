package com.dmall.pms.api.dto.attributevalue.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.attributevalue.common.CommonAttributeValueRequestDTO;

/**
 * @description: 修改属性值请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UpdateAttributeValueRequestDTO", description="修改属性值请求实体")
public class UpdateAttributeValueRequestDTO extends CommonAttributeValueRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
