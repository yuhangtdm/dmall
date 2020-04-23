package com.dmall.oms.api.dto.aftersaleapproval;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 售后单审批请求实体
 * @author: created by hang.yu on 2020/4/15 22:22
 */
@Data
@ApiModel(value = "AfterSaleApprovalRequestDTO", description = "售后单审批请求实体")
public class AfterSaleApprovalRequestDTO implements Serializable {

    private static final long serialVersionUID = 2975626221588805130L;

    @ApiModelProperty(value = "售后单号", required = true, position = 1)
    @NotNull(message = "售后单号不能为空")
    private Long afterSaleId;

    @ApiModelProperty(value = "审核结果", required = true, position = 2)
    @NotNull(message = "审核结果不能为空")
    private Boolean result;

    @ApiModelProperty(value = "处理备注", position = 3)
    private String handleNote;

    @ApiModelProperty(value = "卖家收货人姓名", position = 4)
    private String sellerName;

    @ApiModelProperty(value = "卖家收货人电话", position = 5)
    private String sellerPhone;

    @ApiModelProperty(value = "卖家收货人详细地址", position = 6)
    private String sellerAddressDetail;
}
