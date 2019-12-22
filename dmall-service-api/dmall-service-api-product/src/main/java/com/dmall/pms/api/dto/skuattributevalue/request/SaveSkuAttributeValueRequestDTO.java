package com.dmall.pms.api.dto.skuattributevalue.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.skuattributevalue.common.CommonSkuAttributeValueRequestDTO;

/**
 * @description: 新增sku属性值请求实体
 * @author: created by hang.yu on 2019-12-22 15:09:34
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveSkuAttributeValueRequestDTO", description = "新增sku属性值请求实体")
public class SaveSkuAttributeValueRequestDTO extends CommonSkuAttributeValueRequestDTO {

}
