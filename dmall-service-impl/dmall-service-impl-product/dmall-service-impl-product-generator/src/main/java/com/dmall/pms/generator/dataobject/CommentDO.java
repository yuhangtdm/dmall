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
 * @description: 商品评价表
 * @author: created by hang.yu on 2020-04-12 15:31:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_comment")
public class CommentDO implements Serializable {

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
     * skuid
     */
    private Long skuId;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 子订单号
     */
    private Long subOrderId;

    /**
     * 会员昵称
     */
    private String memberNickName;

    /**
     * 会员头像
     */
    private String memberIcon;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评分 1-5
     */
    private Integer star;

    /**
     * 评价级别 1-好评;2-中评;3-差评
     */
    private Integer level;

    /**
     * 媒体对象 图片或视频,多个以逗号隔开
     */
    private String medias;

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
     * 状态 Y-不可用;N-可用
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private String isDeleted;

}
