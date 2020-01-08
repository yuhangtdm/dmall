package com.dmall.pms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 商品分类表
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_category")
public class CategoryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级id 上级分类编号
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
     * 路径 格式: .parentId.id.
     */
    private String path;

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
