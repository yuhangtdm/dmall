package com.dmall.pms.api.dto.productattribute.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.productattribute.common.CommonProductAttributeRequestDTO;

/**
 * @description: 新增属性值请求实体
 * @author: created by hang.yu on 2019-12-07 21:04:46
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveProductAttributeRequestDTO", description="新增属性值请求实体")
public class SaveProductAttributeRequestDTO extends CommonProductAttributeRequestDTO {

}
