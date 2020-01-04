package com.dmall.pms.service.impl.sku.mapper;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: sku分页vo
 * @author: created by hang.yu on 2020/1/4 17:50
 */
@Data
public class SkuPageVO implements Serializable {

    private static final long serialVersionUID = -7294050181109624050L;


    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商家店铺id
     */
    private Long merchantsId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 商品编码
     */
    private String productNo;

    /**
     * sku编码
     */
    private String skuNo;

    /**
     * sku名称
     */
    private String name;

    /**
     * sku副名称
     */
    private String subName;

    /**
     * sku描述
     */
    private String description;

    /**
     * 商品详情
     */
    private String remark;

    /**
     * sku主图
     */
    private String pic;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * vip价格
     */
    private BigDecimal vipPrice;

    /**
     * 市场价格
     */
    private BigDecimal marketPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 预警库存
     */
    private Integer lowStock;

    /**
     * 锁定库存
     */
    private Integer lockStock;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 上架状态 Y-上架;N-未上架
     */
    private String publishStatus;

    /**
     * 上架时间
     */
    private Date onPublishTime;

    /**
     * 下架时间
     */
    private Date offPublishTime;

    /**
     * 推荐状态 Y-是;N-否
     */
    private String recommendStatus;

    /**
     * 新品状态 Y-是;N-否
     */
    private String newStatus;

    /**
     * 是否是预告sku Y-是;N-否
     */
    private String previewStatus;

    /**
     * 审核状态 1-未审核;2-审核通过;3-审核不通过
     */
    private Integer auditStatus;

    /**
     * 优惠方式
     */
    private String preferentialWay;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 更新人
     */
    private Long modifier;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 状态 N-可用;Y-不可用
     */
    private String isDeleted;
}
