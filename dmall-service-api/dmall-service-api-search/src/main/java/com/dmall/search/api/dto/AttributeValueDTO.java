package com.dmall.search.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @description: 属性值实体
 * @author: created by hang.yu on 2020/3/5 23:30
 */
@Setter
@Getter
@ApiModel(value = "AttributeValueDTO", description = "属性值实体")
public class AttributeValueDTO implements Serializable {

    private static final long serialVersionUID = 6654623467233464860L;

    @ApiModelProperty(value = "属性值id", position = 1)
    private Long attributeValueId;

    @ApiModelProperty(value = "属性名称", position = 2)
    private String attributeValueName;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AttributeValueDTO that = (AttributeValueDTO)o;
        return Objects.equals(attributeValueId, that.attributeValueId) ||
            Objects.equals(attributeValueName, that.attributeValueName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeValueId, attributeValueName);
    }
}
