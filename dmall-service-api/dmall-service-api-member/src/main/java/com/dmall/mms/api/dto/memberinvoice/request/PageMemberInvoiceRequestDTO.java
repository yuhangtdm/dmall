package com.dmall.mms.api.dto.memberinvoice.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 会员发票分页请求实体
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PageMemberInvoiceRequestDTO", description="会员发票分页请求实体")
public class PageMemberInvoiceRequestDTO  extends PageRequestDTO {




    @ApiModelProperty(value = "发票抬头", position = 2)
    private String billHeader;

    @ApiModelProperty(value = "收票人姓名", position = 3)
    private String billReceiverName;

    @ApiModelProperty(value = "收票人电话", position = 4)
    private String billReceiverPhone;

    @ApiModelProperty(value = "收票人邮箱", position = 5)
    private String billReceiverEmail;






}
