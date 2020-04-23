package com.dmall.oms.api.dto.applyreturn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 申请退货请求实体
 * @author: created by hang.yu on 2020/4/14 22:00
 */
@Data
@ApiModel(value = "OrderApplyReturnRequestDTO", description = "申请退货请求实体")
public class OrderApplyReturnRequestDTO implements Serializable {

    private static final long serialVersionUID = -2076730577371710660L;

    @ApiModelProperty(value = "订单项号", required = true, position = 1)
    @NotNull(message = "订单项号不能为空")
    private Long orderItemId;

    @ApiModelProperty(value = "提交数量", required = true, position = 2)
    @NotNull(message = "提交数量不能为空")
    private Integer number;

    @ApiModelProperty(value = "退货原因", required = true, position = 3)
    @NotNull(message = "退货原因不能为空")
    private String reason;

    @ApiModelProperty(value = "退货描述", position = 4)
    private String description;

    @ApiModelProperty(value = "买家发货人姓名", required = true, position = 5)
    @NotBlank(message = "买家发货人姓名不能为空")
    private String buyerName;

    @ApiModelProperty(value = "买家发货人电话", required = true, position = 6)
    @NotBlank(message = "买家发货人电话不能为空")
    private String buyerPhone;

    @ApiModelProperty(value = "买家发货人详细地址", required = true, position = 7)
    @NotBlank(message = "买家发货人详细地址不能为空")
    private String buyerDetailAddress;

    @ApiModelProperty(value = "申请凭证 上传凭证照片,多张以逗号隔开", position = 8)
    private String applyCredentials;
}
