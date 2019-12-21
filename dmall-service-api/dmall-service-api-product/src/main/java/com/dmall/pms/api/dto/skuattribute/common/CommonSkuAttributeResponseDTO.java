package com.dmall.pms.api.dto.skuattribute.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: sku属性值公共响应实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonSkuAttributeResponseDTO", description = "sku属性值公共响应实体")
public class CommonSkuAttributeResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "id", position = 1)
    private Long id;



    @ApiModelProperty(value = "skuid", position = 2)
    private Long skuId;



    @ApiModelProperty(value = "product_attribute_id", position = 3)
    private Long productAttributeId;



    @ApiModelProperty(value = "创建人", position = 4)
    private Long creator;



    @ApiModelProperty(value = "创建时间", position = 5)
    private Date gmtCreated;



    @ApiModelProperty(value = "更新人", position = 6)
    private Long modifier;



    @ApiModelProperty(value = "更新时间", position = 7)
    private Date gmtModified;



    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 8)
    private String isDeleted;


}
