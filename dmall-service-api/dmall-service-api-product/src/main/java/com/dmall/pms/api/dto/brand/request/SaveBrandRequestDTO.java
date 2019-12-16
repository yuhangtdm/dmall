package com.dmall.pms.api.dto.brand.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.brand.common.CommonBrandRequestDTO;

/**
 * @description: 新增品牌请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveBrandRequestDTO", description = "新增品牌请求实体")
public class SaveBrandRequestDTO extends CommonBrandRequestDTO {

}
