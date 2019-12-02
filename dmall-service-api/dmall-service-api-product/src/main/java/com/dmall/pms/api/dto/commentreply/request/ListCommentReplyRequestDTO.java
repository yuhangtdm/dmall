package com.dmall.pms.api.dto.commentreply.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 评价回复列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListCommentReplyRequestDTO", description="评价回复列表请求实体")
public class ListCommentReplyRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




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












}
