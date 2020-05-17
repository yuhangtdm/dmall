package com.dmall.pms.api.dto.product.response.get;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description: 商品基础信息响应实体
 * @author: created by hang.yu on 2019/12/16 11:20
 */
@Data
@ApiModel(value = "BasicProductResponseDTO", description = "商品基础信息响应实体")
public class BasicProductResponseDTO {

    @ApiModelProperty(value = "商品编号", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "商品名称", position = 3)
    private String name;

    @ApiModelProperty(value = "商品介绍", position = 4)
    private String description;

    @ApiModelProperty(value = "商品图片", position = 5)
    private String pic;

    @ApiModelProperty(value = "上市时间", position = 6)
    private Date onMarketTime;

}
