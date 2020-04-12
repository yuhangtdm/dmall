package com.dmall.oms.api.dto.comment;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.oms.api.enums.StarEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 评价商品信息
 * @author: created by hang.yu on 2020/4/12 15:58
 */
@Data
@ApiModel(value = "CommentSkuDTO", description = "评价商品信息")
public class CommentSkuDTO {

    @ApiModelProperty(value = "skuId", position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "评价内容", position = 2)
    @NotNull(message = "评价内容不能为空")
    private String content;

    @ApiModelProperty(value = "评价分数", position = 3)
    @ValueInEnum(StarEnum.class)
    @NotNull(message = "评价分数不能为空")
    private Integer star;

    @ApiModelProperty(value = "媒体对象 图片或视频,多个以逗号隔开", position = 6)
    private String medias;
}
