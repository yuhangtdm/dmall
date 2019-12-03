package com.dmall.pms.generator.dataobject;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: sku表
 * @author: created by hang.yu on 2019-12-03 23:31:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_sku")
public class SkuDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 商品分类id
     */
    private Long categoryId;

    /**
     * 品牌id
     */
    private Long brandId;

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
     * 备注
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
     * 品牌名称
     */
    private String brandName;

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
     * 可用库存
     */
    private Integer availableStock;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * sku规格
     */
    private String skuSpecifications;

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
     * 优惠方式
     */
    private String preferentialWay;

    /**
     * 商品分类id集合 如:1/2/3
     */
    private String cascadeCategoryId;

    /**
     * skuPC端详情富文本
     */
    private String detailHtml;

    /**
     * sku移动端详情富文本
     */
    private String detailMobileHtml;

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
     * 状态 N-可用;Y-不可用
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private String isDeleted;


}
