package com.dmall.mms.api.dto.memberhelp.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 会员-帮助关系表 帮助对会员有用列表请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListMemberHelpRequestDTO", description = "会员-帮助关系表 帮助对会员有用列表请求实体")
public class ListMemberHelpRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "会员id", position = 2)
        private Long memberId;

        @ApiModelProperty(value = "帮助id", position = 3)
        private Long helpId;







}
