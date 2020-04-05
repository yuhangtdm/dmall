package com.dmall.oms.api.dto.deliver;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 发货请求实体
 * @author: created by hang.yu on 2020/4/5 15:36
 */
@Data
@ApiModel(value = "DeliverRequestDTO", description = "发货请求实体")
public class DeliverRequestDTO implements Serializable {

    @ApiModelProperty(value = "子订单id", position = 1)
    @NotNull(message = "子订单id不能为空")
    private Long subOrderId;

    @ApiModelProperty(value = "物流单号", position = 2)
    @NotNull(message = "物流单号不能为空")
    private String logisticsNo;

}
