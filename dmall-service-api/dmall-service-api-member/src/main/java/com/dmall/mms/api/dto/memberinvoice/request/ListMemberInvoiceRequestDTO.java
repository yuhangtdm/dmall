package com.dmall.mms.api.dto.memberinvoice.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 会员发票列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListMemberInvoiceRequestDTO", description = "会员发票列表请求实体")
public class ListMemberInvoiceRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "发票抬头", position = 2)
    private String billHeader;


    @ApiModelProperty(value = "收票人姓名", position = 3)
    private String billReceiverName;


    @ApiModelProperty(value = "收票人电话", position = 4)
    private String billReceiverPhone;


    @ApiModelProperty(value = "收票人邮箱", position = 5)
    private String billReceiverEmail;


}
