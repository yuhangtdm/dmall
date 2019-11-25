package com.dmall.pms.api.dto.brand.request;

import com.dmall.pms.api.dto.brand.common.BrandCommonRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description: 新增品牌请求入参
 * @author: created by hang.yu on 2019/11/19 22:52
 */
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(value = "SaveBrandRequestDTO",description = "新增品牌实体")
public class SaveBrandRequestDTO extends BrandCommonRequestDTO {

    private static final long serialVersionUID = 2837808283931901588L;

}
