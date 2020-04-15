package com.dmall.oms.api.dto.aftersaledetail;

import com.dmall.oms.api.dto.common.BuyerOrderItemDTO;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.api.enums.AfterSaleTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 售后单详情响应实体
 * @author: created by hang.yu on 2020/4/14 23:08
 */
@Data
@ApiModel(value = "AfterSaleDetailResponseDTO", description = "售后单详情响应实体")
public class AfterSaleDetailResponseDTO implements Serializable {

    private static final long serialVersionUID = 806239121249281238L;

    @ApiModelProperty(value = "售后信息", position = 1)
    private AfterSaleDTO afterSaleDTO;

    @ApiModelProperty(value = "售后商品信息", position = 2)
    private BuyerOrderItemDTO buyerOrderItemDTO;
}
