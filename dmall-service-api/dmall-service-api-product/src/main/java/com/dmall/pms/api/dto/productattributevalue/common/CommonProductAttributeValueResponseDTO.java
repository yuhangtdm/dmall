package com.dmall.pms.api.dto.productattributevalue.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 属性值公共响应实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonProductAttributeValueResponseDTO", description = "属性值公共响应实体")
public class CommonProductAttributeValueResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "id", position = 1)
    private Long id;



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



    @ApiModelProperty(value = "创建人", position = 8)
    private Long creator;



    @ApiModelProperty(value = "创建时间", position = 9)
    private Date gmtCreated;



    @ApiModelProperty(value = "更新人", position = 10)
    private Long modifier;



    @ApiModelProperty(value = "更新时间", position = 11)
    private Date gmtModified;



    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 12)
    private String isDeleted;


}
