package com.dmall.mms.api.dto.bankcard.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 会员银行卡公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:04:17
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonBankCardResponseDTO", description="会员银行卡公共响应实体")
public class CommonBankCardResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "id", position = 1)
    private Long id;



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



    @ApiModelProperty(value = "创建人", position = 8)
    private Long creator;



    @ApiModelProperty(value = "创建时间", position = 9)
    private Date gmtCreated;



    @ApiModelProperty(value = "更新人", position = 10)
    private Long modifier;



    @ApiModelProperty(value = "更新时间", position = 11)
    private Date gmtModified;



    @ApiModelProperty(value = "状态 Y-可用;N-不可用", position = 12)
    private String isDeleted;


}
