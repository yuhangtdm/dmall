package com.dmall.pms.api.dto.skuattribute.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: sku属性值列表请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListSkuAttributeRequestDTO", description = "sku属性值列表请求实体")
public class ListSkuAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "skuid", position = 2)
    private Long skuId;


    @ApiModelProperty(value = "product_attribute_id", position = 3)
    private Long productAttributeId;












}
