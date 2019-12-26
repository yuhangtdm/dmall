package com.dmall.pms.api.dto.product.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 商品扩展信息
 * @author: created by hang.yu on 2019/12/10 22:08
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ProductExtDTO", description = "商品扩展信息")
public class ProductExtDTO implements Serializable {

    private static final long serialVersionUID = -4383222923230954323L;

    @ApiModelProperty(value = "商品分类id列表", position = 1)
    @NotNull(message = "商品分类id列表不能为空")
    private List<Long> categoryIds;

    @ApiModelProperty(value = "品牌id", position = 2)
    @NotNull(message = "品牌id不能为空")
    private Long brandId;

    @ApiModelProperty(value = "销售规格", position = 3)
    @Valid
    @NotNull(message = "销售规格不能为空")
    private List<SpecificationsDTO> specifications;

    @ApiModelProperty(value = "卖点", position = 4)
    @Valid
    @NotNull(message = "卖点不能为空")
    private List<SalePointDTO> salePoints;

    @ApiModelProperty(value = "参数", position = 5)
    @Valid
    @NotNull(message = "参数不能为空")
    private List<ParamDTO> params;

}
