package com.dmall.pms.api.dto.sku.request.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: 修改sku请求实体
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UpdateSkuRequestDTO", description = "修改sku请求实体")
public class UpdateSkuRequestDTO  {

    @ApiModelProperty(value = "主键", required = true, position = 1)
    @Valid
    @NotNull(message = "sku基本信息不能为空")
    private BasicSkuRequestDTO basicSkuRequestDTO;

    @ApiModelProperty(value = "商品属性值id列表", position = 2)
    private List<Long> attributeValueList;

    @ApiModelProperty(value = "skuPC端详情富文本", position = 3)
    private String detailHtml;

    @ApiModelProperty(value = "sku移动端详情富文本", position = 4)
    private String detailMobileHtml;

}
