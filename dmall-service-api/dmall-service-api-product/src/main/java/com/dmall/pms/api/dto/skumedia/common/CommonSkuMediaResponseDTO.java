package com.dmall.pms.api.dto.skumedia.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: sku媒体对象公共响应实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonSkuMediaResponseDTO", description = "sku媒体对象公共响应实体")
public class CommonSkuMediaResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "id", position = 1)
    private Long id;



    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;



    @ApiModelProperty(value = "skuid", position = 3)
    private Long skuId;



    @ApiModelProperty(value = "媒体类型 1-图片;2-视频", position = 4)
    private Integer mediaType;



    @ApiModelProperty(value = "媒体key", position = 5)
    private String mediaKey;



    @ApiModelProperty(value = "媒体url", position = 6)
    private String mediaHash;



    @ApiModelProperty(value = "排序", position = 7)
    private Integer sort;



    @ApiModelProperty(value = "创建人", position = 8)
    private Long creator;



    @ApiModelProperty(value = "创建时间", position = 9)
    private Date gmtCreated;



    @ApiModelProperty(value = "更新人", position = 10)
    private Long modifier;



    @ApiModelProperty(value = "更新时间", position = 11)
    private Date gmtModified;



    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 12)
    private String isDeleted;


}
