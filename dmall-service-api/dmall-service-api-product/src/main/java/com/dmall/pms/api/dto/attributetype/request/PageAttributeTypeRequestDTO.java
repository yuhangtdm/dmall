package com.dmall.pms.api.dto.attributetype.request;

import com.dmall.common.dto.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 属性类别分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageAttributeTypeRequestDTO", description = "属性类别分页请求实体")
public class PageAttributeTypeRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "商品分类id", position = 5)
    private Long categoryId;

    @ApiModelProperty(value = "展示名称", position = 6)
    private String showName;

}
