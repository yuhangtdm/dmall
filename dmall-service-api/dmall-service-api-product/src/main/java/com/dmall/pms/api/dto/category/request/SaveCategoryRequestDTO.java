package com.dmall.pms.api.dto.category.request;

import com.dmall.pms.api.dto.category.common.CategoryRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description: 新增分类实体
 * @author: created by hang.yu on 2019/11/24 12:04
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(value = "SaveCategoryRequestDTO", description = "新增分类实体")
@EqualsAndHashCode(callSuper = false)
public class SaveCategoryRequestDTO extends CategoryRequestDTO {

}
