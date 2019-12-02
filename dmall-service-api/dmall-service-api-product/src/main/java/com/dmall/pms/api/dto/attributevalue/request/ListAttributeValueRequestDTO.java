package com.dmall.pms.api.dto.attributevalue.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 属性值列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListAttributeValueRequestDTO", description="属性值列表请求实体")
public class ListAttributeValueRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "商品属性id", position = 2)
    private Long attributeId;


    @ApiModelProperty(value = "属性值", position = 3)
    private String value;


    @ApiModelProperty(value = "属性值配图 规格的属性可能有配图", position = 4)
    private String valuePic;












}
