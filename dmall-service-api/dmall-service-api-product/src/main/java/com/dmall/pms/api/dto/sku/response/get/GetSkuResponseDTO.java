package com.dmall.pms.api.dto.sku.response.get;

import com.dmall.pms.api.dto.product.response.get.ProductAttributeTypeDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description: 查询sku响应实体
 * @author: created by hang.yu on 2019/12/16 21:12
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "GetProductResponseDTO", description =  "查询sku响应实体")
public class GetSkuResponseDTO {

    @ApiModelProperty(value = "查询sku基本信息实体", position = 1)
    private BasicSkuResponseDTO basicSku;

    @ApiModelProperty(value = "销售规格", position = 2)
    private ProductAttributeTypeDTO specifications;

    @ApiModelProperty(value = "销售参数", position = 3)
    private List<ProductAttributeTypeDTO> params;

    @ApiModelProperty(value = "skuPC端详情富文本", position = 4)
    private String detailHtml;

    @ApiModelProperty(value = "sku移动端详情富文本", position = 5)
    private String detailMobileHtml;

    //todo 图片信息
}
