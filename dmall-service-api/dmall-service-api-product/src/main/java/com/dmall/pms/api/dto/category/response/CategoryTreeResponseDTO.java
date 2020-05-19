package com.dmall.pms.api.dto.category.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description: 分类树响应实体
 * @author: created by hang.yu on 2019/11/24 18:30
 */
@Data
@ApiModel(value = "CategoryTreeResponseDTO", description = "分类树响应实体")
public class CategoryTreeResponseDTO implements Serializable {

    private static final long serialVersionUID = 7853335345390628555L;

    @ApiModelProperty(value = "id", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "上级id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "分类名称", position = 3)
    private String name;

    @ApiModelProperty(value = "排序", position = 4)
    private Integer sort;

    @ApiModelProperty(value = "是否打开", position = 6)
    private String open;

    @ApiModelProperty(value = "是否为父元素", position = 7)
    private Boolean isParent = false;

    @ApiModelProperty(value = "是否可选", position = 8)
    private Boolean disabled = false;

    @ApiModelProperty(value = "子元素", position = 9)
    private List<CategoryTreeResponseDTO> children = Lists.newArrayList();

    @ApiModelProperty(value = "自定义数据", position = 10)
    private Map<String, Object> basicData = Maps.newHashMap();
}
