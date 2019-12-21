package com.dmall.pms.api.dto.productattributevalue.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.productattributevalue.common.CommonProductAttributeValueRequestDTO;

/**
 * @description: 新增属性值请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveProductAttributeValueRequestDTO", description = "新增属性值请求实体")
public class SaveProductAttributeValueRequestDTO extends CommonProductAttributeValueRequestDTO {

}
