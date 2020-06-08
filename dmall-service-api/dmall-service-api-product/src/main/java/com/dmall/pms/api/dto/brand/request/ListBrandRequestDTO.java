package com.dmall.pms.api.dto.brand.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Set;

/**
 * @description: 品牌列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@ApiModel(value = "ListBrandRequestDTO", description = "品牌列表请求实体")
public class ListBrandRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌名称", position = 1)
    private String name;

    @ApiModelProperty(value = "英文名称", position = 2)
    private String englishName;

    @ApiModelProperty(value = "首字母", position = 3)
    @Length(max = 1, min = 1, message = "首字母长度固定1位")
    private String firstLetter;

    @ApiModelProperty(value = "商品分类id", required = true, position = 4)
    private Set<Long> categoryIds;
}
