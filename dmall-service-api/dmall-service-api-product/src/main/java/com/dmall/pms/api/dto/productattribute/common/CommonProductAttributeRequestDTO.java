package com.dmall.pms.api.dto.productattribute.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 属性值公共请求实体
 * @author: created by hang.yu on 2019-12-07 21:04:46
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonProductAttributeRequestDTO", description="属性值公共请求实体")
public class CommonProductAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;


    @ApiModelProperty(value = "属性分类id", position = 3)
    private Long attributeTypeId;


    @ApiModelProperty(value = "商品属性id", position = 4)
    private Long attributeId;


    @ApiModelProperty(value = "属性类型 1-规格;2-参数", position = 5)
    private Integer attributeType;












}
