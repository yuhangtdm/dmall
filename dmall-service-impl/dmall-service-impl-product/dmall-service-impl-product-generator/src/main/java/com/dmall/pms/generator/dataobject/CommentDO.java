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
 * @author: created by hang.yu on 2019-12-16 15:14:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_comment")
public class CommentDO implements Serializable {

    private static final long serialVersionUID=1L;

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
     * skuid
     */
    private Long skuId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单项id
     */
    private String orderItemId;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 星级 1-5星，1-差评;2,3-中评;4,5-好评
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
     * 会员昵称
     */
    private String memberNickName;

    /**
     * 评价ip
     */
    private String memberIp;

    /**
     * 会员头像
     */
    private String memberIcon;

    /**
     * 是否显示在评论列表中 Y-显示;N-不显示
     */
    private String showStatus;

    /**
     * 点赞数
     */
    private Integer praiseCount;

    /**
     * 回复数
     */
    private Integer replyCount;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * sku编号
     */
    private String skuNo;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单项编号
     */
    private String orderItemNo;

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
