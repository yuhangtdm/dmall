package com.dmall.oms.api.dto.aftersalepage;

import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.api.enums.AfterSaleTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @description: 售后分页请求实体
 * @author: created by hang.yu on 2020/4/14 23:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AfterSalePageRequestDTO", description = "售后分页请求实体")
public class AfterSalePageRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "售后单号", position = 5)
    private Long afterSaleId;

    @ApiModelProperty(value = "订单号", position = 5)
    private Long orderId;

    @ApiModelProperty(value = "订单项号", position = 6)
    private Long orderItemId;

    @ApiModelProperty(value = "子订单号", position = 7)
    private Long subOrderId;

    @ApiModelProperty(value = "售后类型", position = 8)
    @ValueInEnum(AfterSaleTypeEnum.class)
    private Integer type;

    @ApiModelProperty(value = "售后状态", position = 9)
    @ValueInEnum(AfterSaleStatusEnum.class)
    private Integer status;

    @ApiModelProperty(value = "skuId", position = 10)
    private Long skuId;

    @ApiModelProperty(value = "开始申请时间", position = 11)
    private Date startApplyTime;

    @ApiModelProperty(value = "结束申请时间", position = 11)
    private Date endApplyTime;
}
