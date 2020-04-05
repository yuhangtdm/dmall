package com.dmall.oms.api.dto.deliver;

import com.dmall.oms.api.enums.DeliverStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description: 发货分页响应实体
 * @author: created by hang.yu on 2020/4/5 10:42
 */
@Data
@ApiModel(value = "PageOrderResponseDTO", description = "发货分页响应实体")
public class DeliverOrderPageResponseDTO {

    @ApiModelProperty(value = "订单号", position = 1)
    private Long orderId;

    @ApiModelProperty(value = "子订单号", position = 2)
    private Long subOrderId;

    @ApiModelProperty(value = "发货状态", position = 3)
    private DeliverStatusEnum deliverStatus;

    @ApiModelProperty(value = "仓库id", position = 4)
    private Long warehouseId;

    @ApiModelProperty(value = "会员id", position = 5)
    private Long memberId;

    @ApiModelProperty(value = "物流单号", position = 8)
    private String logisticsNo;

    @ApiModelProperty(value = "发货时间", position = 9)
    private Date deliveryTime;

    @ApiModelProperty(value = "订单创建时间", position = 10)
    private Date orderCreateTime;

    @ApiModelProperty(value = "收货人姓名", position = 11)
    private String receiverName;

    @ApiModelProperty(value = "收货人电话", position = 12)
    private String receiverPhone;

    @ApiModelProperty(value = "收货人省份/直辖市", position = 13)
    private String receiverProvince;

    @ApiModelProperty(value = "收货人城市", position = 14)
    private String receiverCity;

    @ApiModelProperty(value = "收货人区/县", position = 15)
    private String receiverRegion;

    @ApiModelProperty(value = "收货人详细地址", position = 16)
    private String receiverDetailAddress;

    @ApiModelProperty(value = "发货人id", position = 17)
    private Long deliverId;

    @ApiModelProperty(value = "发货人名称", position = 18)
    private String deliverName;
}
