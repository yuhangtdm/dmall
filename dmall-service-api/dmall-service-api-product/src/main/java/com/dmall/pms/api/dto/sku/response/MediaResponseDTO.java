package com.dmall.pms.api.dto.sku.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @description: 媒体响应实体
 * @author: created by hang.yu on 2019/12/17 14:28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "MediaResponseDTO", description = "媒体响应实体")
public class MediaResponseDTO {

    @ApiModelProperty(value = "skuMediaId", position = 1)
    private Long skuMediaId;

    @ApiModelProperty(value = "key", position = 2)
    private String key;

    @ApiModelProperty(value = "hash", position = 3)
    private String hash;

    @ApiModelProperty(value = "url", position = 4)
    private String url;

    @ApiModelProperty(value = "媒体类型,1-图片,2-视频", position = 5)
    @NotNull(message = "媒体类型不能为空")
    private Integer mediaType;

    @ApiModelProperty(value = "排序", position = 6)
    private Integer sort;
}
