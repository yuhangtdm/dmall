package com.dmall.pms.api.dto.sku.response.get;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: sku扩展响应实体
 * @author: created by hang.yu on 2019/12/16 17:41
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SkuExtResponseDTO", description = "sku扩展响应实体")
public class SkuExtResponseDTO {

    @ApiModelProperty(value = "skuPC端详情富文本", position = 1)
    private String detailHtml;

    @ApiModelProperty(value = "sku移动端详情富文本", position = 2)
    private String detailMobileHtml;

    @ApiModelProperty(value = "sku规格json", position = 3)
    private String skuSpecificationsJson;

    @ApiModelProperty(value = "sku卖点json", position = 4)
    private String skuSalePointJson;

    @ApiModelProperty(value = "sku参数json", position = 5)
    private String skuParamJson;
}
