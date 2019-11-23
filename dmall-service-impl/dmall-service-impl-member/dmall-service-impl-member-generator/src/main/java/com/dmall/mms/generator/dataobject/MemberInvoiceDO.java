package com.dmall.mms.generator.dataobject;

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
 * 会员发票表
 * </p>
 *
 * @author hang.yu
 * @since 2019-10-29
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
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发票抬头
     */
    @TableField("bill_header")
    private String billHeader;

    /**
     * 收票人姓名
     */
    @TableField("bill_receiver_name")
    private String billReceiverName;

    /**
     * 收票人电话
     */
    @TableField("bill_receiver_phone")
    private String billReceiverPhone;

    /**
     * 收票人邮箱
     */
    @TableField("bill_receiver_email")
    private String billReceiverEmail;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created", fill = FieldFill.INSERT)
    private Date gmtCreated;

    /**
     * 更新人
     */
    private Long modifier;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 状态 N-可用;Y-不可用
     */
    @TableField("is_deleted")
    private String isDeleted;


}
