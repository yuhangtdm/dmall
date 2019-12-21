package com.dmall.pms.api.dto.skuext.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: sku扩展公共请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonSkuExtRequestDTO", description = "sku扩展公共请求实体")
public class CommonSkuExtRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "sku_id", position = 2)
    private Long skuId;


    @ApiModelProperty(value = "skuPC端详情富文本", position = 3)
    private String detailHtml;


    @ApiModelProperty(value = "sku移动端详情富文本", position = 4)
    private String detailMobileHtml;


    @ApiModelProperty(value = "sku属性json", position = 5)
    private String skuAttributeJson;












}
