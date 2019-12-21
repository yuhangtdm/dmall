package com.dmall.pms.api.dto.skuattribute.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.skuattribute.common.CommonSkuAttributeRequestDTO;

/**
 * @description: 新增sku属性值请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveSkuAttributeRequestDTO", description = "新增sku属性值请求实体")
public class SaveSkuAttributeRequestDTO extends CommonSkuAttributeRequestDTO {

}
