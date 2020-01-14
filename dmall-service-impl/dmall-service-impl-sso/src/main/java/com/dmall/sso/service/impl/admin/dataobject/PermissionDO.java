package com.dmall.sso.service.impl.admin.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 资源表
 * @author: created by hang.yu on 2020-01-11 18:49:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bms_permission")
public class PermissionDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 资源码
     */
    private String code;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 资源类型 1-目录;2-菜单;3-接口地址
     */
    private Integer type;

    /**
     * 资源地址
     */
    private String uri;

    /**
     * 资源请求方式 1-GET;2-POST;3-PUT;4-DELETE
     */
    private String method;

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
