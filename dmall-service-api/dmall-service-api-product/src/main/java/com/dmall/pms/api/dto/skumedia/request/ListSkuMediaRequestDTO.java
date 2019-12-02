package com.dmall.pms.api.dto.skumedia.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: sku媒体对象列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListSkuMediaRequestDTO", description="sku媒体对象列表请求实体")
public class ListSkuMediaRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;


    @ApiModelProperty(value = "skuid", position = 3)
    private String skuId;


    @ApiModelProperty(value = "媒体类型 1-图片;2-视频", position = 4)
    private Integer mediaType;


    @ApiModelProperty(value = "媒体key", position = 5)
    private String mediaKey;


    @ApiModelProperty(value = "媒体url", position = 6)
    private String mediaUrl;


    @ApiModelProperty(value = "排序", position = 7)
    private Integer sort;


    @ApiModelProperty(value = "商品编号", position = 8)
    private String productNo;


    @ApiModelProperty(value = "sku编号", position = 9)
    private String skuNo;












}
