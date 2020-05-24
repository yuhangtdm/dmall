package com.dmall.pms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 属性表
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_attribute")
public class AttributeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 展示名称
     */
    private String showName;

    /**
     * 属性类型 1-普通属性;2-公共属性
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
     * 是否支持手动新增 Y-支持;N-不支持
     */
    private String handAddStatus;

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
