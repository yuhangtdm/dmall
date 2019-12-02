package com.dmall.pms.api.dto.attributevalue.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 属性值公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonAttributeValueResponseDTO", description="属性值公共响应实体")
public class CommonAttributeValueResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "id", position = 1)
    private Long id;



    @ApiModelProperty(value = "商品属性id", position = 2)
    private Long attributeId;



    @ApiModelProperty(value = "属性值", position = 3)
    private String value;



    @ApiModelProperty(value = "属性值配图 规格的属性可能有配图", position = 4)
    private String valuePic;



    @ApiModelProperty(value = "创建人", position = 5)
    private Long creator;



    @ApiModelProperty(value = "创建时间", position = 6)
    private Date gmtCreated;



    @ApiModelProperty(value = "更新人", position = 7)
    private Long modifier;



    @ApiModelProperty(value = "更新时间", position = 8)
    private Date gmtModified;



    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 9)
    private String isDeleted;


}
