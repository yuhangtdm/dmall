package com.dmall.bms.api.dto.deliverywarehouse.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.deliverywarehouse.common.CommonDeliveryWarehouseRequestDTO;

/**
 * @description: 修改商家发货仓库请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateDeliveryWarehouseRequestDTO", description = "修改商家发货仓库请求实体")
public class UpdateDeliveryWarehouseRequestDTO extends CommonDeliveryWarehouseRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
