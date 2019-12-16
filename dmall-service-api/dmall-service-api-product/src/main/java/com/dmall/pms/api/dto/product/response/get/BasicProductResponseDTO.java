package com.dmall.pms.api.dto.product.response.get;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/16 11:20
 */
@Data
@Accessors(chain = true)
@ApiModel(value="BasicProductResponseDTO", description="商品基础信息响应实体")
public class BasicProductResponseDTO {

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

    @ApiModelProperty(value = "上市时间", position = 9)
    private Date onMarketTime;

}
