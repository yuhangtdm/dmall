package com.dmall.pms.api.dto.productattributevalue.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 属性值公共请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonProductAttributeValueRequestDTO", description = "属性值公共请求实体")
public class CommonProductAttributeValueRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;


    @ApiModelProperty(value = "商品属性id", position = 3)
    private Long attributeId;


    @ApiModelProperty(value = "Y-是;N-不是", position = 4)
    private String isSellingPoint;


    @ApiModelProperty(value = "Y-是;N-不是", position = 5)
    private String isSpecifications;


    @ApiModelProperty(value = "属性值", position = 6)
    private String attributeValue;


    @ApiModelProperty(value = "属性配图", position = 7)
    private String pic;












}
