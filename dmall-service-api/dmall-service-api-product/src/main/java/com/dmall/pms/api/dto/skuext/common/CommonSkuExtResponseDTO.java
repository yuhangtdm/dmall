package com.dmall.pms.api.dto.skuext.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: sku扩展公共响应实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonSkuExtResponseDTO", description = "sku扩展公共响应实体")
public class CommonSkuExtResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "id", position = 1)
    private Long id;



    @ApiModelProperty(value = "sku_id", position = 2)
    private Long skuId;



    @ApiModelProperty(value = "skuPC端详情富文本", position = 3)
    private String detailHtml;



    @ApiModelProperty(value = "sku移动端详情富文本", position = 4)
    private String detailMobileHtml;



    @ApiModelProperty(value = "sku属性json", position = 5)
    private String skuAttributeJson;



    @ApiModelProperty(value = "创建人", position = 6)
    private Long creator;



    @ApiModelProperty(value = "创建时间", position = 7)
    private Date gmtCreated;



    @ApiModelProperty(value = "更新人", position = 8)
    private Long modifier;



    @ApiModelProperty(value = "更新时间", position = 9)
    private Date gmtModified;



    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 10)
    private String isDeleted;


}
