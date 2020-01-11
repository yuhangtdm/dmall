package com.dmall.pms.api.dto.product.common;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.dto.product.enums.UnitEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 修改商品基础信息实体
 * @author: created by hang.yu on 2019/12/10 21:43
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BasicProductRequestDTO", description = "修改商品基础信息实体")
public class BasicProductRequestDTO implements Serializable {

    private static final long serialVersionUID = 1619159990632457989L;

    @ApiModelProperty(value = "商品名称", required = true, position = 1)
    @NotBlank(message = "商品名称不能为空")
    private String name;

    @ApiModelProperty(value = "商品介绍", position = 2)
    private String description;

    @ApiModelProperty(value = "商品单位", position = 3)
    @ValueInEnum(UnitEnum.class)
    private String unit;

    @ApiModelProperty(value = "商品重量", position = 4)
    private BigDecimal weight;

    @ApiModelProperty(value = "上市时间", position = 5)
    private Date onMarketTime;

    @ApiModelProperty(value = "商品图片", position = 6)
    private String pic;

}
