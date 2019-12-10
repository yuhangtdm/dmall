package com.dmall.pms.api.dto.product.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/10 21:43
 */
@Data
@Accessors(chain = true)
@ApiModel(value="BasicProductDTO", description="新增商品基础信息实体")
public class BasicProductDTO implements Serializable {

    private static final long serialVersionUID = 1619159990632457989L;

    @ApiModelProperty(value = "商品名称", position = 1)
    @NotBlank(message = "商品名称不能为空")
    private String name;

    @ApiModelProperty(value = "商品介绍", position = 2)
    private String description;

    @ApiModelProperty(value = "商品单位", position = 3)
    @NotBlank(message = "商品单位不能为空")
    private String unit;

    @ApiModelProperty(value = "商品重量", position = 4)
    @NotNull(message = "商品重量不能为空")
    private BigDecimal weight;

    @ApiModelProperty(value = "上市时间", position =5)
    @NotNull(message = "上市时间不能为空")
    private Date onMarketTime;

    @ApiModelProperty(value = "商品图片", position = 6)
    @NotBlank(message = "商品图片不能为空")
    private String pic;

}
