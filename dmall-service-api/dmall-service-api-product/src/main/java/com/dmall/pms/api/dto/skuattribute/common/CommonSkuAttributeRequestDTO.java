package com.dmall.pms.api.dto.skuattribute.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: sku属性公共请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonSkuAttributeRequestDTO", description="sku属性公共请求实体")
public class CommonSkuAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;


    @ApiModelProperty(value = "skuid", position = 3)
    private Long skuId;


    @ApiModelProperty(value = "属性id", position = 4)
    private Long attributeId;


    @ApiModelProperty(value = "属性值id", position = 5)
    private Long valueId;


    @ApiModelProperty(value = "属性名称", position = 6)
    private String attributeName;


    @ApiModelProperty(value = "属性值", position = 7)
    private String value;


    @ApiModelProperty(value = "类型 1-规格;2-参数", position = 8)
    private Integer type;


    @ApiModelProperty(value = "规格配图", position = 9)
    private String pic;


    @ApiModelProperty(value = "商品编号", position = 10)
    private String productNo;


    @ApiModelProperty(value = "sku编号", position = 11)
    private String skuNo;












}
