package com.dmall.mms.api.dto.memberinvoice.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 会员发票公共响应实体
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonMemberInvoiceResponseDTO", description="会员发票公共响应实体")
public class CommonMemberInvoiceResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "id", position = 1)
    private Long id;



    @ApiModelProperty(value = "发票抬头", position = 2)
    private String billHeader;



    @ApiModelProperty(value = "收票人姓名", position = 3)
    private String billReceiverName;



    @ApiModelProperty(value = "收票人电话", position = 4)
    private String billReceiverPhone;



    @ApiModelProperty(value = "收票人邮箱", position = 5)
    private String billReceiverEmail;



    @ApiModelProperty(value = "创建人", position = 6)
    private Long creator;



    @ApiModelProperty(value = "创建时间", position = 7)
    private Date gmtCreated;



    @ApiModelProperty(value = "更新人", position = 8)
    private Long modifier;



    @ApiModelProperty(value = "更新时间", position = 9)
    private Date gmtModified;



    @ApiModelProperty(value = "状态 Y-可用;N-不可用", position = 10)
    private String isDeleted;


}
