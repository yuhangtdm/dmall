package com.dmall.mms.api.dto.memberviewsku.request;

import com.dmall.common.dto.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @description: 会员浏览历史记录分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageMemberViewSkuRequestDTO", description = "会员浏览历史记录分页请求实体")
public class PageMemberViewSkuRequestDTO extends PageRequestDTO {


    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;

    @ApiModelProperty(value = "skuid", position = 3)
    private Long skuId;

    @ApiModelProperty(value = "商品id", position = 4)
    private String productId;

    @ApiModelProperty(value = "商品编号", position = 5)
    private String productNo;

    @ApiModelProperty(value = "sku编号", position = 6)
    private String skuNo;

    @ApiModelProperty(value = "浏览时价格", position = 7)
    private BigDecimal price;


}
