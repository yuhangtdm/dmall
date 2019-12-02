package com.dmall.pms.api.dto.attributetype.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.attributetype.common.CommonAttributeTypeRequestDTO;

/**
 * @description: 新增属性分类请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveAttributeTypeRequestDTO", description="新增属性分类请求实体")
public class SaveAttributeTypeRequestDTO extends CommonAttributeTypeRequestDTO {

}
