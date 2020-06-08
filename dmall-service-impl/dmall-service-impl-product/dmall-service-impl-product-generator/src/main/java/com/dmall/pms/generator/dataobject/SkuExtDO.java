package com.dmall.pms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: sku扩展表
 * @author: created by hang.yu on 2019-12-22 16:09:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_sku_ext")
public class SkuExtDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * skuPC端详情富文本
     */
    private String detailHtml;

    /**
     * sku移动端详情富文本
     */
    private String detailMobileHtml;

    /**
     * sku规格json
     */
    private String skuSpecificationsJson;

    /**
     * sku卖点json
     */
    private String skuSalePointJson;

    /**
     * sku参数json
     */
    private String skuParamJson;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreated;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
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
