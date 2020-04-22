package com.dmall.pms.api.dto.sku.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 新增sku扩展实体
 * @author: created by hang.yu on 2019/12/16 17:41
 */
@Data
@ApiModel(value = "SaveSkuExtRequestDTO", description = "新增sku扩展实体")
public class SaveSkuExtRequestDTO implements Serializable {

    private static final long serialVersionUID = -2420463980116701606L;

    @ApiModelProperty(value = "商品id", required = true, position = 1)
    @NotNull(message = "商品id不能为空")
    private Long productId;

    @ApiModelProperty(value = "skuId", required = true, position = 2)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "skuPC端详情富文本", required = true, position = 3)
    @NotNull(message = "skuPC端详情富文本不能为空")
    private String detailHtml;

    @ApiModelProperty(value = "sku移动端详情富文本", position = 4)
    private String detailMobileHtml;
}
