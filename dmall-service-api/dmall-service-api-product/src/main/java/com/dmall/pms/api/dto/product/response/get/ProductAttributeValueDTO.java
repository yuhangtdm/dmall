package com.dmall.pms.api.dto.product.response.get;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 商品属性值响应实体
 * @author: created by hang.yu on 2019/12/16 12:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ProductAttributeValueDTO", description = "商品属性值响应实体")
public class ProductAttributeValueDTO implements Serializable {

    private static final long serialVersionUID = -5238483585926410027L;

    @ApiModelProperty(value = "属性值", position = 1)
    private String attributeValue;

    @ApiModelProperty(value = "属性值图片", position = 2)
    private String attributePic;

}
