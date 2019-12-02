package com.dmall.pms.api.dto.skuattribute.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.skuattribute.common.CommonSkuAttributeRequestDTO;

/**
 * @description: 新增sku属性请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveSkuAttributeRequestDTO", description="新增sku属性请求实体")
public class SaveSkuAttributeRequestDTO extends CommonSkuAttributeRequestDTO {

}
