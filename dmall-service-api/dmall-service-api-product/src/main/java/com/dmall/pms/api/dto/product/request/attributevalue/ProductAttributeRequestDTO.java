package com.dmall.pms.api.dto.product.request.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description: 商品请求属性信息
 * @author: created by hang.yu on 2020/1/4 10:52
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ProductAttributeRequestDTO", description = "商品请求属性信息")
public class ProductAttributeRequestDTO {

    @ApiModelProperty(value = "销售规格", required = true, position = 3)
    @Valid
    @NotNull(message = "销售规格不能为空")
    @Size(min = 1, message = "销售规格不能为空")
    private List<SpecificationsRequestDTO> specifications;

    @ApiModelProperty(value = "卖点", position = 4)
    @Valid
    private List<SalePointRequestDTO> salePoints;

    @ApiModelProperty(value = "参数", position = 5)
    @Valid
    private List<ParamRequestDTO> params;
}
