package com.dmall.oms.api.dto.suborderjob.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.oms.api.dto.suborderjob.common.CommonSubOrderJobRequestDTO;

/**
 * @description: 新增子订单job表 请求实体
 * @author: created by hang.yu on 2020-04-18 21:31:26
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveSubOrderJobRequestDTO", description = "新增子订单job表 请求实体")
public class SaveSubOrderJobRequestDTO extends CommonSubOrderJobRequestDTO {

}
