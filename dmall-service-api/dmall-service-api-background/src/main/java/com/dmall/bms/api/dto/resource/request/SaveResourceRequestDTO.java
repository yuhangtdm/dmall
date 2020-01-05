package com.dmall.bms.api.dto.resource.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.resource.common.CommonResourceRequestDTO;

/**
 * @description: 新增资源请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveResourceRequestDTO", description = "新增资源请求实体")
public class SaveResourceRequestDTO extends CommonResourceRequestDTO {

}
