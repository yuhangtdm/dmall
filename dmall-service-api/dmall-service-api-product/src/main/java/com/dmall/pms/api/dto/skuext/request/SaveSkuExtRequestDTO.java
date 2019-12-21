package com.dmall.pms.api.dto.skuext.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.skuext.common.CommonSkuExtRequestDTO;

/**
 * @description: 新增sku扩展请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveSkuExtRequestDTO", description = "新增sku扩展请求实体")
public class SaveSkuExtRequestDTO extends CommonSkuExtRequestDTO {

}
