package com.dmall.mms.api.dto.bankcard.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 会员银行卡列表请求实体
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListBankCardRequestDTO", description="会员银行卡列表请求实体")
public class ListBankCardRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;


    @ApiModelProperty(value = "银行名称", position = 3)
    private String bankName;


    @ApiModelProperty(value = "持卡人姓名", position = 4)
    private String name;


    @ApiModelProperty(value = "持卡人手机号", position = 5)
    private String mobile;


    @ApiModelProperty(value = "持卡人身份证号", position = 6)
    private String idNo;


    @ApiModelProperty(value = "银行卡类型 1-储蓄卡;2-信用卡", position = 7)
    private Integer type;












}
