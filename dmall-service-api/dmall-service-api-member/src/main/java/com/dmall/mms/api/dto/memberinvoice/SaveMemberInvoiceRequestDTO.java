package com.dmall.mms.api.dto.memberinvoice;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.mms.api.enums.InvoiceContentEnum;
import com.dmall.mms.api.enums.InvoiceHeaderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 新增会员发票请求实体
 * @author: created by hang.yu on 2020/3/27 23:36
 */
@Data
@ApiModel(value = "SaveMemberInvoiceRequestDTO", description = "新增会员发票请求实体")
public class SaveMemberInvoiceRequestDTO implements Serializable {

    @ApiModelProperty(value = "发票抬头 1-个人;2-单位", required = true, position = 2)
    @NotNull(message = "发票抬头不能为空")
    @ValueInEnum(InvoiceHeaderEnum.class)
    private Integer invoiceHeader;

    @ApiModelProperty(value = "个人名称", position = 3)
    private String personalName;

    @ApiModelProperty(value = "收票人手机号", required = true, position = 4)
    @NotBlank(message = "收票人手机号不能为空")
    private String receiverPhone;

    @ApiModelProperty(value = "收票人邮箱", position = 5)
    private String receiverEmail;

    @ApiModelProperty(value = "单位名称", position = 6)
    private String companyName;

    @ApiModelProperty(value = "纳税人识别号", position = 7)
    private String customerTaxNumber;

    @ApiModelProperty(value = "发票内容 1-商品类别;2-商品明细", required = true, position = 8)
    @NotNull(message = "发票内容不能为空")
    @ValueInEnum(InvoiceContentEnum.class)
    private Integer invoiceContent;
}
