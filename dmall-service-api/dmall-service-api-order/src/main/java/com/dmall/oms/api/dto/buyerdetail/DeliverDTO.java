package com.dmall.oms.api.dto.buyerdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 配送信息响应实体
 * @author: created by hang.yu on 2020/4/5 22:30
 */
@Data
@ApiModel(value = "DeliverDTO", description = "配送信息响应实体")
public class DeliverDTO implements Serializable {

    private static final long serialVersionUID = -8205380475715188666L;

    @ApiModelProperty(value = "物流单号", position = 1)
    private String logisticsNo;

    @ApiModelProperty(value = "快递公司", position = 2)
    private String logisticsCompany;
}
