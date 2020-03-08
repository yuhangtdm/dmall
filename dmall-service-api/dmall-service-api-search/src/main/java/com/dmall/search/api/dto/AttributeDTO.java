package com.dmall.search.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @description: 属性实体
 * @author: created by hang.yu on 2020/3/5 23:30
 */
@Setter
@Getter
@ApiModel(value = "AttributeDTO", description = "属性实体")
public class AttributeDTO implements Serializable {

    private static final long serialVersionUID = -926306086594052455L;

    @ApiModelProperty(value = "属性id", position = 1)
    private Long attributeId;

    @ApiModelProperty(value = "属性名称", position = 2)
    private String attributeName;

    @ApiModelProperty(value = "1-不可筛选;2-单选;3-多选", position = 3)
    private Integer canScreen;

    @ApiModelProperty(value = "属性值集合", position = 4)
    private List<AttributeValueDTO> attributeValues;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeDTO that = (AttributeDTO) o;
        return Objects.equals(attributeId, that.attributeId) ||
                Objects.equals(attributeName, that.attributeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeId, attributeName);
    }
}
