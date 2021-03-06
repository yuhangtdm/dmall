package com.dmall.oms.api.dto.totrade.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 发票实体
 * @author: created by hang.yu on 2020/3/26 22:51
 */
@Data
@ApiModel(value = "InvoiceResponseDTO", description = "发票实体")
public class InvoiceResponseDTO implements Serializable {

    private static final long serialVersionUID = -850112885912797017L;

    @ApiModelProperty(value = "发票id", position = 1)
    private Long id;

    @ApiModelProperty(value = "发票抬头 1-个人;2-单位", position = 2)
    private Integer invoiceHeader;

    @ApiModelProperty(value = "个人名称", position = 3)
    private String personalName;

    @ApiModelProperty(value = "收票人手机号", position = 4)
    private String receiverPhone;

    @ApiModelProperty(value = "收票人邮箱", position = 5)
    private String receiverEmail;

    @ApiModelProperty(value = "单位名称", position = 6)
    private String companyName;

    @ApiModelProperty(value = "纳税人识别号", position = 7)
    private String customerTaxNumber;

    @ApiModelProperty(value = "发票内容 1-商品类别;2-商品明细", position = 8)
    private Integer invoiceContent;

}
