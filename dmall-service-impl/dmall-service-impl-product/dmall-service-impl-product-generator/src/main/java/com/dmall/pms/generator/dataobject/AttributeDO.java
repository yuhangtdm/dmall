package com.dmall.pms.generator.dataobject;

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
 * <p>
 * 属性表
 * </p>
 *
 * @author hang.yu
 * @since 2019-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_attribute")
public class AttributeDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 属性分类id
     */
    private Long attributeTypeId;

    /**
     * 商品分类id
     */
    private Long categoryId;

    /**
     * 商品分类id集合 如，1/2/3
     */
    private String cascadeCategoryId;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类型 1-规格;2-参数;
     */
    private Integer type;

    /**
     * 属性录入方式 1-手工录入;2-从列表获取
     */
    private Integer inputType;

    /**
     * 可选值列表 以逗号隔开
     */
    private String inputList;

    /**
     * 属性选择类型 1-单选;2-多选
     */
    private String selectType;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否支持手动新增 Y-支持;N-不支持
     */
    private String handAddStatus;

    /**
     * 属性值数量
     */
    private Integer valueCount;

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
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private String isDeleted;


}
