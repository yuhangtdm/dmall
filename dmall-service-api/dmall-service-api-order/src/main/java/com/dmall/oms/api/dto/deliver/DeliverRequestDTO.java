package com.dmall.oms.api.dto.deliver;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 发货请求实体
 * @author: created by hang.yu on 2020/4/5 15:36
 */
@Data
@ApiModel(value = "DeliverRequestDTO", description = "发货请求实体")
public class DeliverRequestDTO implements Serializable {

    private static final long serialVersionUID = -6067644433036853472L;

    @ApiModelProperty(value = "子订单id", required = true, position = 1)
    @NotNull(message = "子订单id不能为空")
    private Long subOrderId;

    @ApiModelProperty(value = "物流单号", required = true, position = 2)
    @NotNull(message = "物流单号不能为空")
    private String logisticsNo;

    @ApiModelProperty(value = "快递公司", required = true, position = 3)
    @NotBlank(message = "快递公司不能为空")
    private String logisticsCompany;

    @ApiModelProperty(value = "快递费", required = true, position = 4)
    @NotNull(message = "快递费不能为空")
    private BigDecimal expressFee;

}
