package com.dmall.pms.api.dto.brand.request;

import com.dmall.common.dto.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 品牌分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageBrandRequestDTO", description = "品牌分页请求实体")
public class PageBrandRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "名称", position = 5)
    private String name;

    @ApiModelProperty(value = "英文名称", position = 6)
    private String englishName;

    @ApiModelProperty(value = "首字母", position = 7)
    private String firstLetter;

}
