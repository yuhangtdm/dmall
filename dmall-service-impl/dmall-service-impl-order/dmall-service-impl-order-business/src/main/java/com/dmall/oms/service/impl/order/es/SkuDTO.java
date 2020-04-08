package com.dmall.oms.service.impl.order.es;

import lombok.Data;

/**
 * @description: SkuDTO
 * @author: created by hang.yu on 2020/4/8 23:13
 */
@Data
public class SkuDTO {

    /**
     * skuId
     */
    private Long skuId;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku主图
     */
    private String skuMainPic;
}
