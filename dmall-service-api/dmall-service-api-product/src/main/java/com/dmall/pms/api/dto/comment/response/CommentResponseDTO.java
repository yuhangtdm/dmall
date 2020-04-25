package com.dmall.pms.api.dto.comment.response;

import com.dmall.common.dto.ResponsePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 商品评价响应实体
 * @author: created by hang.yu on 2020/4/25 17:08
 */
@Data
@ApiModel(value = "CommentResponseDTO", description = "商品评价响应实体")
public class CommentResponseDTO implements Serializable {

    private static final long serialVersionUID = 1788168265292904367L;

    @ApiModelProperty(value = "分页数据", position = 1)
    private ResponsePage<CommentPageResponseDTO> page;

    @ApiModelProperty(value = "评价总数", position = 2)
    private Integer commentCount;

    @ApiModelProperty(value = "好评数", position = 3)
    private Integer goodCommentCount;

    @ApiModelProperty(value = "中评数", position = 4)
    private Integer middleCommentCount;

    @ApiModelProperty(value = "差评数", position = 5)
    private Integer badCommentCount;

    @ApiModelProperty(value = "有图评价数", position = 6)
    private Integer hasPicCommentCount;

}
