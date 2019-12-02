package com.dmall.pms.api.dto.skumedia.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: sku媒体对象分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PageSkuMediaRequestDTO", description="sku媒体对象分页请求实体")
public class PageSkuMediaRequestDTO  extends PageRequestDTO {




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
