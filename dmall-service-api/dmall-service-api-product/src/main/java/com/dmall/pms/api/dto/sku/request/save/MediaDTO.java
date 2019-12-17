package com.dmall.pms.api.dto.sku.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 14:28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "MeidaDTO", description = "媒体实体")
public class MediaDTO {

    @ApiModelProperty(value = "key", position = 1)
    private String key;

    @ApiModelProperty(value = "hash", position = 2)
    private String hash;

    @ApiModelProperty(value = "媒体类型,1-图片,2-视频", position = 3)
    private Integer mediaType;

    @ApiModelProperty(value = "排序", position = 4)
    private Integer sort;
}
