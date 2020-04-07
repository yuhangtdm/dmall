package com.dmall.oms.api.dto.sellerdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 物流信息响应实体
 * @author: created by hang.yu on 2020/4/6 11:35
 */
@Data
@ApiModel(value = "LogisticsDTO", description = "物流信息响应实体")
public class LogisticsDTO {

    @ApiModelProperty(value = "物流公司", position = 1)
    private String logisticsCompany;

    @ApiModelProperty(value = "快递费", position = 2)
    private BigDecimal expressFee;

    @ApiModelProperty(value = "物流单号", position = 3)
    private String logisticsNo;
}
