package com.dmall.pms.api.dto.product.response.get;

import com.dmall.pms.api.dto.attributetype.enums.AttributeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 属性组全集
 * @author: created by hang.yu on 2019/12/16 11:23
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ProductAttributeTypeDTO", description = "商品属性组响应实体")
public class ProductAttributeTypeDTO implements Serializable {

    private static final long serialVersionUID = 5732388982400999359L;

    @ApiModelProperty(value = "属性分类id", position = 1)
    private Long attributeTypeId;

    @ApiModelProperty(value = "属性分类展示名称", position = 2)
    private String attributeShowName;

    @ApiModelProperty(value = "属性分类类型", position = 3)
    private AttributeTypeEnum type;

    @ApiModelProperty(value = "属性列表", position = 3)
    private List<ProductAttributeDTO> attributes;

}
