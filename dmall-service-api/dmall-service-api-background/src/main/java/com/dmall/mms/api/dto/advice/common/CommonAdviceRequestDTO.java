package com.dmall.mms.api.dto.advice.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 会员意见表 公共请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonAdviceRequestDTO", description = "会员意见表 公共请求实体")
public class CommonAdviceRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;

    @ApiModelProperty(value = "意见内容", position = 3)
    private String content;

    @ApiModelProperty(value = "附图", position = 4)
    private String pics;







}
