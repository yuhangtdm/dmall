package com.dmall.pms.generator.dataobject;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author yuhang
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_product")
public class ProductDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商家店铺id
     */
    private Long merchantsId;

    /**
     * 商品分类id
     */
    private Long categoryId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 运费模板id
     */
    private Long feightTemplateId;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品图片
     */
    private String pic;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 单位
     */
    private String unit;

    /**
     * 重量 默认克
     */
    private BigDecimal weight;

    /**
     * 备注
     */
    private String remark;

    /**
     * 商品属性
     */
    private String productAttribute;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 上市时间
     */
    private Date onMarketTime;

    /**
     * 商品最高价
     */
    private BigDecimal maxPrice;

    /**
     * 商品最低价
     */
    private BigDecimal minPrice;

    /**
     * 销量
     */
    private Integer saleCount;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 评论量
     */
    private Integer commentCount;

    /**
     * 好评量
     */
    private Integer goodCommentCount;

    /**
     * 中评量
     */
    private Integer middleCommentCount;

    /**
     * 差评量
     */
    private Integer badCommentCount;

    /**
     * 好评率
     */
    private BigDecimal goodRate;

    /**
     * 平均评分
     */
    private BigDecimal commentScore;

    /**
     * 商品分类id集合 如:1/2/3
     */
    private String cascadeCategoryId;

    /**
     * 增值业务
     */
    private String addServices;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreated;

    /**
     * 更新人
     */
    private Long modifier;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 状态 Y,可用;N:不可用
     */
    private String isDeleted;


}
