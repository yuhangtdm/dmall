package com.dmall.oms.api.dto.buyerdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 发票信息响应实体
 * @author: created by hang.yu on 2020/4/5 22:37
 */
@Data
@ApiModel(value = "InvoiceDTO", description = "发票信息响应实体")
public class InvoiceDTO {

    @ApiModelProperty(value = "发票抬头", position = 1)
    private String invoiceHeader;

    @ApiModelProperty(value = "纳税人识别号", position = 2)
    private String customerTaxNumber;

    @ApiModelProperty(value = "发票内容 1-商品类别;2-商品明细", position = 3)
    private String invoiceContent;

    @ApiModelProperty(value = "发票号码", position = 4)
    private String invoiceNumber;

    @ApiModelProperty(value = "发票抬头类型", position = 5)
    private Integer invoiceHeaderType;
}
