package com.dmall.oms.api.dto.sellerdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 发货人响应实体
 * @author: created by hang.yu on 2020/4/7 22:02
 */
@Data
@ApiModel(value = "DeliverDTO", description = "发货人响应实体")
public class DeliverDTO implements Serializable {

    private static final long serialVersionUID = -8364228356608587244L;

    @ApiModelProperty(value = "发货人id", position = 1)
    private Long deliverId;

    @ApiModelProperty(value = "发货人姓名", position = 2)
    private String deliverName;

    @ApiModelProperty(value = "发货人电话", position = 3)
    private String deliverPhone;

    @ApiModelProperty(value = " 发货省份/直辖市", position = 4)
    private String deliverProvince;

    @ApiModelProperty(value = "发货城市", position = 5)
    private String deliverCity;

    @ApiModelProperty(value = "发货人区/县", position = 6)
    private String deliverRegion;

    @ApiModelProperty(value = "发货详细地址", position = 7)
    private String deliverDetailAddress;

    @ApiModelProperty(value = "发货时间", position = 8)
    private Date deliverTime;
}
