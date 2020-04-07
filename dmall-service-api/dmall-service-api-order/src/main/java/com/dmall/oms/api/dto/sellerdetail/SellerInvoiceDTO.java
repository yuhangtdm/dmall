package com.dmall.oms.api.dto.sellerdetail;

import com.dmall.oms.api.enums.InvoiceContentEnum;
import com.dmall.oms.api.enums.InvoiceHeaderTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 发票信息响应实体
 * @author: created by hang.yu on 2020/4/5 22:37
 */
@Data
@ApiModel(value = "SellerInvoiceDTO", description = "发票信息响应实体")
public class SellerInvoiceDTO {

    @ApiModelProperty(value = "是否已开票", position = 1)
    private String openInvoice;

    @ApiModelProperty(value = "发票号码", position = 2)
    private String invoiceNumber;

    @ApiModelProperty(value = "发票类型", position = 3)
    private Integer invoiceType;

    @ApiModelProperty(value = "发票抬头", position = 4)
    private InvoiceHeaderTypeEnum invoiceHeader;

    @ApiModelProperty(value = "个人名称", position = 5)
    private String personalName;

    @ApiModelProperty(value = "发票内容", position = 6)
    private InvoiceContentEnum invoiceContent;

    @ApiModelProperty(value = "单位名称", position = 7)
    private String companyName;

    @ApiModelProperty(value = "纳税人识别号", position = 8)
    private String customerTaxNumber;

    @ApiModelProperty(value = "收票人电话", position = 9)
    private String invoiceReceiverPhone;

    @ApiModelProperty(value = "收票人电话", position = 10)
    private String invoiceReceiverEmail;
}
