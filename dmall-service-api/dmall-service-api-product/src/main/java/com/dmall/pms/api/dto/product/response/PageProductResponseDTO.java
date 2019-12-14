package com.dmall.pms.api.dto.product.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 商品公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value="PageProductResponseDTO", description="商品分页响应实体")
public class PageProductResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "商品编号", position = 2)
    private String productNo;

    @ApiModelProperty(value = "商品分类id", position = 3)
    private Long categoryId;

    @ApiModelProperty(value = "品牌id", position = 4)
    private Long brandId;

    @ApiModelProperty(value = "商品名称", position = 5)
    private String name;

    @ApiModelProperty(value = "商品图片", position = 6)
    private String pic;

    @ApiModelProperty(value = "品牌名称", position = 7)
    private String brandName;

    @ApiModelProperty(value = "商品分类名称", position = 8)
    private String cascadeCategoryName;

    @ApiModelProperty(value = "上市时间", position = 9)
    private Date onMarketTime;

    @ApiModelProperty(value = "创建人", position = 10)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 11)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 12)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 13)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 14)
    private String isDeleted;

}
