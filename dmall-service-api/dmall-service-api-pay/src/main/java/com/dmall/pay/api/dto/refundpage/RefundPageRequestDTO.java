package com.dmall.pay.api.dto.refundpage;

import com.dmall.common.dto.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 退款明细分页请求实体
 * @author: created by hang.yu on 2020/4/16 23:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RefundPageResponseDTO", description = "退款明细分页请求实体")
public class RefundPageRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "售后单号", position = 5)
    private Long afterSaleId;

}
