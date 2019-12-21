package com.dmall.pms.api.dto.skuattribute.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: sku属性值公共请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonSkuAttributeRequestDTO", description = "sku属性值公共请求实体")
public class CommonSkuAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "skuid", position = 2)
    private Long skuId;


    @ApiModelProperty(value = "product_attribute_id", position = 3)
    private Long productAttributeId;












}
