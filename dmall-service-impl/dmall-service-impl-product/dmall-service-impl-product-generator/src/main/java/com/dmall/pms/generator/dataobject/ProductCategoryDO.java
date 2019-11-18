package com.dmall.pms.generator.dataobject;

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
 * 商品分类表
 * </p>
 *
 * @author yuhang
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_product_category")
public class ProductCategoryDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级id 上级分类编号：0表示一级分类
     */
    private Long parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * PC图标
     */
    private String icon;

    /**
     * 移动端图标
     */
    private String mobileIcon;

    /**
     * 级别 1-1级;2-2级;3-3级
     */
    private Integer level;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 关键字 用于搜索
     */
    private String keywords;

    /**
     * 是否热门 Y-是;N-否
     */
    private String hotStatus;

    /**
     * 是否显示在导航栏 Y-是;N-否
     */
    private String navStatus;

    /**
     * 品牌数量
     */
    private Integer brandCount;

    /**
     * 属性分类数量
     */
    private Integer attributeCategoryCount;

    /**
     * 属性数量
     */
    private Integer attributeCount;

    /**
     * 路径 .parentId.id的这种格式
     */
    private String path;

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
     * 状态 Y-可用;N-不可用
     */
    private String isDeleted;


}
