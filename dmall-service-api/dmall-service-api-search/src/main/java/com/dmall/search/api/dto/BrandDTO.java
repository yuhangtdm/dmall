package com.dmall.search.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 品牌返回实体
 * @author: created by hang.yu on 2020/3/8 14:40
 */
@Data
@ApiModel(value = "BrandDTO", description = "品牌列表返回实体")
public class BrandDTO implements Serializable {

    private static final long serialVersionUID = 7779011935224882481L;

    @ApiModelProperty(value = "品牌id", position = 1)
    private Long brandId;

    @ApiModelProperty(value = "品牌名称", position = 2)
    private String brandName;

    @ApiModelProperty(value = "品牌英文名称", position = 3)
    private String brandEnglishName;

    @ApiModelProperty(value = "品牌logo", position = 4)
    private String brandLogo;
}
