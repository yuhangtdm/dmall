package com.dmall.mms.api.dto.memberhelp.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 会员-帮助关系表 帮助对会员有用公共请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonMemberHelpRequestDTO", description="会员-帮助关系表 帮助对会员有用公共请求实体")
public class CommonMemberHelpRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;


    @ApiModelProperty(value = "帮助id", position = 3)
    private Long helpId;












}
