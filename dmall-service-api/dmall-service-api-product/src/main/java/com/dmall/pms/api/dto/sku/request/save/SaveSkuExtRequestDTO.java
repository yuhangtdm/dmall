package com.dmall.pms.api.dto.sku.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @description: 新增sku扩展实体
 * @author: created by hang.yu on 2019/12/16 17:41
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SaveSkuExtRequestDTO", description = "新增sku扩展实体")
public class SaveSkuExtRequestDTO {

    @ApiModelProperty(value = "属性值id", position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "skuPC端详情富文本", position = 2)
    private String detailHtml;

    @ApiModelProperty(value = "sku移动端详情富文本", position = 3)
    private String detailMobileHtml;
}
