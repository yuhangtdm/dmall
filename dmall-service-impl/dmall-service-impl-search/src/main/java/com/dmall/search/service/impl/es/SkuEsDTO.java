package com.dmall.search.service.impl.es;

import com.dmall.search.api.dto.AttributeDTO;
import com.dmall.search.api.dto.BrandDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 导入es的dto
 * @author: created by hang.yu on 2020/3/5 22:03
 */
@Data
public class SkuEsDTO implements Serializable {

    private static final long serialVersionUID = 9023610880315830853L;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku编号
     */
    private String skuNo;

    /**
     * sku价格
     */
    private BigDecimal skuPrice;

    /**
     * sku副名称
     */
    private String skuSubName;

    /**
     * sku描述
     */
    private String skuDescription;

    /**
     * sku主图
     */
    private String skuMainPic;

    /**
     * sku库存
     */
    private Integer skuStock;

    /**
     * sku评论数量
     */
    private Integer skuCommentCount;

    /**
     * sku销量
     */
    private Integer skuSaleCount;

    /**
     * sku上架时间
     */
    private String skuOnPublishTime;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 品牌id
     */
    private BrandDTO brandDTO;

    /**
     * 分类id集合
     */
    private List<Long> categoryIds;

    /**
     * 属性值id集合
     */
    private List<AttributeDTO> attributes;

    /**
     * 属性值id集合
     */
    private List<Long> attributeValueIds;

}
