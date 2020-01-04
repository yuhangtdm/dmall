package com.dmall.pms.api.dto.sku.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: 媒体实体
 * @author: created by hang.yu on 2019/12/17 14:28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "MediaDTO", description = "媒体实体")
public class MediaDTO {

    @ApiModelProperty(value = "skuMediaId", position = 1)
    private Long skuMediaId;

    @ApiModelProperty(value = "key", position = 2)
    private String key;

    @ApiModelProperty(value = "hash", position = 3)
    private String hash;

    @ApiModelProperty(value = "url", position = 4)
    private String url;

    @ApiModelProperty(value = "媒体类型,1-图片,2-视频", position = 5)
    private Integer mediaType;

    @ApiModelProperty(value = "排序", position = 6)
    private Integer sort;
}
