package com.dmall.pms.api.dto.attributetype.request;

import com.dmall.component.web.entity.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 属性分类分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageAttributeTypeRequestDTO", description = "属性分类分页请求实体")
public class PageAttributeTypeRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "商品分类id", position = 1)
    private Long categoryId;

    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    @ApiModelProperty(value = "展示名称", position = 3)
    private String showName;

}
