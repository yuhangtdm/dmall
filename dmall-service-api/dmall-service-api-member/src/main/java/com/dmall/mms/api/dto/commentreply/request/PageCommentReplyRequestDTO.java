package com.dmall.mms.api.dto.commentreply.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 评价回复分页请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageCommentReplyRequestDTO", description =  "评价回复分页请求实体")
public class PageCommentReplyRequestDTO  extends PageRequestDTO {



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
