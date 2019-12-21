package com.dmall.pms.api.dto.skumedia.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.skumedia.common.CommonSkuMediaRequestDTO;

/**
 * @description: 新增sku媒体对象请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveSkuMediaRequestDTO", description = "新增sku媒体对象请求实体")
public class SaveSkuMediaRequestDTO extends CommonSkuMediaRequestDTO {

}
