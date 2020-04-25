package com.dmall.pms.api.dto.skuaudit.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: sku审核记录列表请求实体
 * @author: created by hang.yu on 2020-04-25 14:49:35
 */
@Data
@ApiModel(value = "ListSkuAuditRequestDTO", description = "sku审核记录列表请求实体")
public class ListSkuAuditRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;

    @ApiModelProperty(value = "skuId", position = 3)
    private String skuId;

    @ApiModelProperty(value = "审核结果", position = 4)
    private Integer status;

}
