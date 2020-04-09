package com.dmall.oms.api.dto.createorder;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 创建订单请求实体
 * @author: created by hang.yu on 2020/3/28 15:48
 */
@Data
@ApiModel(value = "CreateOrderRequestDTO", description = "创建订单请求实体")
public class CreateOrderRequestDTO {

    @ApiModelProperty(value = "sku信息", position = 1)
    @NotNull(message = "sku信息不能为空")
    @Size(min = 1, message = "sku信息不能为空")
    private List<OrderSkuRequestDTO> orderSku;

    @ApiModelProperty(value = "收货地址信息", position = 1)
    @NotNull(message = "收货地址信息不能为空")
    @Valid
    private OrderAddressRequestDTO orderAddressRequestDTO;

    @ApiModelProperty(value = "发票信息", position = 3)
    @NotNull(message = "发票信息不能为空")
    @Valid
    private OrderInvoiceRequestDTO orderInvoiceRequestDTO;

    @ApiModelProperty(value = "sku总价", position = 4)
    @NotNull(message = "sku总价不能为空")
    private BigDecimal totalSkuPrice;

    @ApiModelProperty(value = "运费", position = 5)
    @NotNull(message = "运费不能为空")
    private BigDecimal freightPrice;

    @ApiModelProperty(value = "订单总价", position = 6)
    @NotNull(message = "订单总价不能为空")
    private BigDecimal orderPrice;

    @ApiModelProperty(value = "订单来源", position = 7)
    @NotNull(message = "订单来源不能为空")
    @ValueInEnum(OrderStatusEnum.class)
    private Integer source;

    @ApiModelProperty(value = "订单备注", position = 8)
    private String remark;

    @ApiModelProperty(value = "orderKey", position = 9)
    @NotBlank(message = "orderKey不能为空")
    private String orderKey;
}
