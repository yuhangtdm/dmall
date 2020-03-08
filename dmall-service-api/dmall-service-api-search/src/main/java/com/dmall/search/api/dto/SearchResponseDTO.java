package com.dmall.search.api.dto;

import com.dmall.common.dto.ResponsePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 搜索返回实体
 * @author: created by hang.yu on 2020/3/5 23:23
 */
@Data
@ApiModel(value = "SearchResponseDTO", description = "搜索平台返回实体")
public class SearchResponseDTO {

    @ApiModelProperty(value = "分页数据", position = 1)
    private ResponsePage<SkuEsResponseDTO> page;

    @ApiModelProperty(value = "可选品牌列表", position = 2)
    private List<BrandDTO> brandList;

    @ApiModelProperty(value = "可选属性列表", position = 3)
    private List<AttributeDTO> attributeList;

}
