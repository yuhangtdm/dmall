package com.dmall.pms.api.dto.attributetype.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 属性分类列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListAttributeTypeRequestDTO", description = "属性分类列表请求实体")
public class ListAttributeTypeRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品分类id", position = 2)
    private Long categoryId;

    @ApiModelProperty(value = "名称", position = 4)
    private String name;

    @ApiModelProperty(value = "展示名称", position = 5)
    private String showName;

}
