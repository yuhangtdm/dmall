package com.dmall.oms.api.dto.myaftersalepage;

import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 我的售后单分页请求实体
 * @author: created by hang.yu on 2020/4/16 22:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MyAfterSalePageRequestDTO", description = "我的售后单分页请求实体")
public class MyAfterSalePageRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "售后单号", position = 5)
    private Long afterSaleId;

    @ApiModelProperty(value = "订单号", position = 6)
    private Long orderId;

    @ApiModelProperty(value = "售后状态", position = 7)
    @ValueInEnum(AfterSaleStatusEnum.class)
    private Integer status;
}
