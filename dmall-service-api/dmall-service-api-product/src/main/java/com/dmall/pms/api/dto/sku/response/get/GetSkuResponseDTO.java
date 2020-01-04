package com.dmall.pms.api.dto.sku.response.get;

import com.dmall.pms.api.dto.sku.common.MediaDTO;
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
@ApiModel(value = "GetSkuResponseDTO" , description = "查询sku响应实体" )
public class GetSkuResponseDTO {

    @ApiModelProperty(value = "sku基本信息实体" , position = 1)
    private BasicSkuResponseDTO basicSku;

    @ApiModelProperty(value = "sku媒体实体列表" , position = 2)
    private List<MediaDTO> mediaList;

    @ApiModelProperty(value = "sku属性值实体" , position = 3)
    private SkuAttributeValueResponseDTO skuAttributeValue;

    @ApiModelProperty(value = "sku扩展响应实体" , position = 4)
    private SkuExtResponseDTO skuExt;
}
