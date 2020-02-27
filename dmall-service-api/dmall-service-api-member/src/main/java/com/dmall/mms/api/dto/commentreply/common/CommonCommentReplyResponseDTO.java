package com.dmall.mms.api.dto.commentreply.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 评价回复公共响应实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonCommentReplyResponseDTO", description = "评价回复公共响应实体")
public class CommonCommentReplyResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "评价id", position = 2)
    private Long commentId;

    @ApiModelProperty(value = "父级id", position = 3)
    private String parentId;

    @ApiModelProperty(value = "内容", position = 4)
    private String content;

    @ApiModelProperty(value = "会员昵称", position = 5)
    private String memberNickName;

    @ApiModelProperty(value = "会员头像", position = 6)
    private String memberIcon;

    @ApiModelProperty(value = "回复类型 1-会员;2-本人;3-管理员", position = 7)
    private Integer type;

    @ApiModelProperty(value = "点赞数", position = 8)
    private String praseCount;

    @ApiModelProperty(value = "被回复人昵称", position = 9)
    private String nickName;

    @ApiModelProperty(value = "创建人", position = 10)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 11)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 12)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 13)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 14)
    private String isDeleted;


}
