package com.dmall.mms.api.dto.advice.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 会员意见表 公共响应实体
 * @author: created by hang.yu on 2020-02-23 19:42:02
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonAdviceResponseDTO", description = "会员意见表 公共响应实体")
public class CommonAdviceResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;

    @ApiModelProperty(value = "意见内容", position = 3)
    private String content;

    @ApiModelProperty(value = "附图", position = 4)
    private String pics;

    @ApiModelProperty(value = "创建人", position = 5)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 6)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 7)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 8)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 9)
    private String isDeleted;


}
