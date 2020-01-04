package com.dmall.pms.api.dto.sku.request.save;

import com.dmall.component.web.validate.ValueInEnum;
import com.dmall.pms.api.dto.sku.enums.MediaTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @description: 媒体请求实体
 * @author: created by hang.yu on 2019/12/17 14:28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "MediaRequestDTO", description = "媒体请求实体")
public class MediaRequestDTO {

    @ApiModelProperty(value = "skuMediaId", position = 1)
    private Long skuMediaId;

    @ApiModelProperty(value = "key", required = true, position = 2)
    @NotNull(message = "key不能为空")
    private String key;

    @ApiModelProperty(value = "hash", required = true, position = 3)
    @NotNull(message = "hash不能为空")
    private String hash;

    @ApiModelProperty(value = "url", required = true, position = 4)
    @NotNull(message = "url不能为空")
    private String url;

    @ApiModelProperty(value = "媒体类型,1-图片,2-视频", required = true, position = 5)
    @NotNull(message = "媒体类型不能为空")
    @ValueInEnum(MediaTypeEnum.class)
    private Integer mediaType;

    @ApiModelProperty(value = "排序", required = true, position = 6)
    @NotNull(message = "排序不能为空")
    private Integer sort;

}
