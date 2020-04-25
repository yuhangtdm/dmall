package com.dmall.mms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 会员发票表
 * @author: created by hang.yu on 2020-02-23 19:42:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mms_member_invoice")
public class MemberInvoiceDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 发票抬头 1-个人;2-单位
     */
    private String invoiceHeader;

    /**
     * 个人名称
     */
    private String personalName;

    /**
     * 收票人手机号
     */
    private String receiverPhone;

    /**
     * 收票人邮箱
     */
    private String receiverEmail;

    /**
     * 单位名称
     */
    private String companyName;

    /**
     * 纳税人识别号
     */
    private String customerTaxNumber;

    /**
     * 发票内容 1-商品类别;2-商品明细
     */
    private Integer invoiceContent;

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
