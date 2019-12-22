package com.dmall.pms.api.dto.skuattributevalue.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.skuattributevalue.common.CommonSkuAttributeValueRequestDTO;

/**
 * @description: 修改sku属性值请求实体
 * @author: created by hang.yu on 2019-12-22 15:09:34
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateSkuAttributeValueRequestDTO", description = "修改sku属性值请求实体")
public class UpdateSkuAttributeValueRequestDTO extends CommonSkuAttributeValueRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
