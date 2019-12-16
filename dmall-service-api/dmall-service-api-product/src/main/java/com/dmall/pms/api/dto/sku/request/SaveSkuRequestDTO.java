package com.dmall.pms.api.dto.sku.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.sku.common.CommonSkuRequestDTO;

/**
 * @description: 新增sku请求实体
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveSkuRequestDTO", description = "新增sku请求实体")
public class SaveSkuRequestDTO extends CommonSkuRequestDTO {

}
