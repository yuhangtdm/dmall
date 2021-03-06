package com.dmall.mms.api.dto.memberinvoice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改会员发票请求实体
 * @author: created by hang.yu on 2020/3/27 23:36
 */
@Data
@ApiModel(value = "UpdateMemberInvoiceRequestDTO", description = "修改会员发票请求实体")
public class UpdateMemberInvoiceRequestDTO implements Serializable {

    @ApiModelProperty(value = "发票id", required = true, position = 1)
    @NotNull(message = "发票id不能为空")
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
