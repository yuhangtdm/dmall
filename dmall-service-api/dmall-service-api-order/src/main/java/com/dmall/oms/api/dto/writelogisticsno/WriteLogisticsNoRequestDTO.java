package com.dmall.oms.api.dto.writelogisticsno;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 填写物流单号实体
 * @author: created by hang.yu on 2020/4/15 22:41
 */
@Data
@ApiModel(value = "WriteLogisticsNoRequestDTO", description = "填写物流单号实体")
public class WriteLogisticsNoRequestDTO {

    @ApiModelProperty(value = "售后单号", position = 1)
    @NotNull(message = "售后单号不能为空")
    private Long afterSaleId;

    @ApiModelProperty(value = "物流单号", position = 1)
    @NotBlank(message = "物流单号不能为空")
    private String logisticsNo;
}
