package com.dmall.pms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @description: 属性值表
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pms_product_attribute_value")
public class ProductAttributeValueDO implements Serializable {

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
     * 属性类别id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAttributeValueDO that = (ProductAttributeValueDO) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(attributeId, that.attributeId) &&
                Objects.equals(attributeValue, that.attributeValue) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
