package com.dmall.pms.api.dto.attributetype.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 属性分类公列表响应实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PageAttributeTypeResponseDTO", description = "属性分类列表响应实体")
public class PageAttributeTypeResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "商品分类id", position = 2)
    private Long categoryId;

    @ApiModelProperty(value = "商品分类名称 如 电脑/电脑整机/笔记本", position = 3)
    private String cascadeCategoryName;

    @ApiModelProperty(value = "名称", position = 4)
    private String name;

    @ApiModelProperty(value = "展示名称", position = 5)
    private String showName;

    @ApiModelProperty(value = "排序", position = 6)
    private Integer sort;

    @ApiModelProperty(value = "创建人", position = 7)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 9)
    private Date gmtCreated;

    @ApiModelProperty(value = "修改人", position = 10)
    private Long modifier;

    @ApiModelProperty(value = "修改时间", position = 12)
    private Date gmtModified;

    @ApiModelProperty(value = "状态,N-可用;Y-不可用", position = 13)
    private String isDeleted;

}
