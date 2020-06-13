package com.dmall.pms.api.dto.product.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 商品公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@ApiModel(value = "PageProductResponseDTO", description = "商品分页响应实体")
public class PageProductResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "品牌id", position = 4)
    private Long brandId;

    @ApiModelProperty(value = "商品名称", position = 5)
    private String name;

    @ApiModelProperty(value = "商品图片", position = 6)
    private String pic;

    @ApiModelProperty(value = "品牌名称", position = 7)
    private String brandName;

    @ApiModelProperty(value = "单位", position = 10)
    private String unit;

    @ApiModelProperty(value = "重量", position = 11)
    private BigDecimal weight;

    @ApiModelProperty(value = "创建人", position = 12)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 13)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 14)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 15)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 16)
    private String isDeleted;

}
