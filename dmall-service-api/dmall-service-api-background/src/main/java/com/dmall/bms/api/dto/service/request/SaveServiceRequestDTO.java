package com.dmall.bms.api.dto.service.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.service.common.CommonServiceRequestDTO;

/**
 * @description: 新增服务表 请求实体
 * @author: created by hang.yu on 2020-02-20 21:36:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveServiceRequestDTO", description = "新增服务表 请求实体")
public class SaveServiceRequestDTO extends CommonServiceRequestDTO {

}
