package com.dmall.oms.api.dto.totrade.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 跳转结算页响应实体
 * @author: created by hang.yu on 2020/3/26 22:32
 */
@Data
@ApiModel(value = "ToTradeResponseDTO", description = "跳转结算页响应实体")
public class ToTradeResponseDTO implements Serializable {

    private static final long serialVersionUID = 7085335355165896539L;

    @ApiModelProperty(value = "收货地址列表", position = 1)
    private List<AddressResponseDTO> addressList;

    @ApiModelProperty(value = "sku列表", position = 2)
    private List<SkuResponseDTO> skuList;

    @ApiModelProperty(value = "sku列表", position = 3)
    private InvoiceResponseDTO invoiceResponseDTO;

    @ApiModelProperty(value = "商品总数量", position = 4)
    private Integer skuTotalNumber;

    @ApiModelProperty(value = "商品总价格", position = 5)
    private BigDecimal skuTotalMoney;

    @ApiModelProperty(value = "运费", position = 6)
    private BigDecimal freightMoney;

}
