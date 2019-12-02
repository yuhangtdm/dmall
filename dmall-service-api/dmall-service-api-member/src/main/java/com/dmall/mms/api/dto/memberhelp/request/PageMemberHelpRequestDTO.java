package com.dmall.mms.api.dto.memberhelp.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 会员-帮助关系表 帮助对会员有用分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PageMemberHelpRequestDTO", description="会员-帮助关系表 帮助对会员有用分页请求实体")
public class PageMemberHelpRequestDTO  extends PageRequestDTO {




    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;

    @ApiModelProperty(value = "帮助id", position = 3)
    private Long helpId;






}
