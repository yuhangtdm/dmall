package com.dmall.mms.api.dto.advice.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 会员意见表 分页请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageAdviceRequestDTO", description =  "会员意见表 分页请求实体")
public class PageAdviceRequestDTO  extends PageRequestDTO {



        @ApiModelProperty(value = "会员id", position = 2)
        private Long memberId;

        @ApiModelProperty(value = "意见内容", position = 3)
        private String content;

        @ApiModelProperty(value = "附图", position = 4)
        private String pics;







}
