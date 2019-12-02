package com.dmall.mms.api.dto.membercolletionsubject.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 会员收藏专题表 公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonMemberColletionSubjectResponseDTO", description="会员收藏专题表 公共响应实体")
public class CommonMemberColletionSubjectResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "创建人", position = 1)
    private Long creator;



    @ApiModelProperty(value = "创建时间", position = 2)
    private Date gmtCreated;



    @ApiModelProperty(value = "更新人", position = 3)
    private Long modifier;



    @ApiModelProperty(value = "更新时间", position = 4)
    private Date gmtModified;



    @ApiModelProperty(value = "状态 Y-可用;N-不可用", position = 5)
    private String isDeleted;


}
