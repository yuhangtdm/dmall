package com.dmall.pms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: sku扩展表
 * @author: created by hang.yu on 2019-12-22 16:09:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SkuExtDTO", description = "sku扩展表")
public class SkuExtDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "sku_id", position = 2)
    private Long skuId;

    @ApiModelProperty(value = "skuPC端详情富文本", position = 3)
    private String detailHtml;

    @ApiModelProperty(value = "sku移动端详情富文本", position = 4)
    private String detailMobileHtml;

    @ApiModelProperty(value = "sku规格json", position = 5)
    private String skuSpecificationsJson;

    @ApiModelProperty(value = "sku卖点json", position = 6)
    private String skuSalePointJson;

    @ApiModelProperty(value = "sku参数json", position = 7)
    private String skuParamJson;

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
