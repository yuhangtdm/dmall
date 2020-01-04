package com.dmall.pms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 属性值表
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_product_attribute_value")
public class ProductAttributeValueDO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 属性分类id
     */
    private Long attributeTypeId;

    /**
     * 商品属性id
     */
    private Long attributeId;

    /**
     * 是否卖点,Y-是;N-不是
     */
    private String isSellingPoint;

    /**
     * 是否规格,Y-是;N-不是
     */
    private String isSpecifications;

    /**
     * 是否参数,Y-是;N-不是
     */
    private String isParam;

    /**
     * 属性值
     */
    private String attributeValue;

    /**
     * 属性配图
     */
    private String pic;

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
