package com.dmall.pms.api.dto.product.request.update;

import com.dmall.pms.api.dto.product.request.attribute.AttributeTypeDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description: 修改商品请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UpdateProductRequestDTO", description = "修改商品请求实体")
public class UpdateProductRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "商品名称", position = 1)
    private String name;

    @ApiModelProperty(value = "商品介绍", position = 2)
    private String description;

    @ApiModelProperty(value = "商品单位", position = 3)
    private String unit;

    @ApiModelProperty(value = "商品重量", position = 4)
    private BigDecimal weight;

    @ApiModelProperty(value = "上市时间", position = 5)
    private Date onMarketTime;

    @ApiModelProperty(value = "销售规格", position = 6)
    @Valid
    @NotNull(message = "销售规格不能为空")
    private AttributeTypeDTO specifications;

    @ApiModelProperty(value = "销售参数", position = 7)
    @Valid
    @NotNull(message = "销售参数不能为空")
    private List<AttributeTypeDTO> params;

}
