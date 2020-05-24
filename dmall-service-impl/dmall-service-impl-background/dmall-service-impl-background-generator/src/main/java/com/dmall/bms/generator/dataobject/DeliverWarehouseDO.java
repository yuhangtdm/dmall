package com.dmall.bms.generator.dataobject;

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
 * @description: 商家发货仓库表
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bms_deliver_warehouse")
public class DeliverWarehouseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 商家店铺id
     */
    private Long merchantsId;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库所在省/直辖市
     */
    private String province;

    /**
     * 仓库所在市
     */
    private String city;

    /**
     * 仓库所在区
     */
    private String region;

    /**
     * 仓库详细地址
     */
    private String detailAddress;

    /**
     * 仓库联系人姓名
     */
    private String contactName;

    /**
     * 仓库联系人电话
     */
    private String contactTel;

    /**
     * 默认发货地址 Y-是,N-否
     */
    private String deliveryStatus;

    /**
     * 默认收货地址 Y-是,N-否
     */
    private String receiveStatus;

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
