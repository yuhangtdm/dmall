package com.dmall.bms.api.dto.deliverywarehouse.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.deliverywarehouse.common.CommonDeliveryWarehouseRequestDTO;

/**
 * @description: 新增商家发货仓库请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveDeliveryWarehouseRequestDTO", description = "新增商家发货仓库请求实体")
public class SaveDeliveryWarehouseRequestDTO extends CommonDeliveryWarehouseRequestDTO {

}
