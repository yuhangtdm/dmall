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
 * 会员银行卡表
 * </p>
 *
 * @author hang.yu
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mms_bank_card")
public class BankCardDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    @TableField("member_id")
    private Long memberId;

    /**
     * 银行名称
     */
    @TableField("bank_name")
    private String bankName;

    /**
     * 持卡人姓名
     */
    private String name;

    /**
     * 持卡人手机号
     */
    private String mobile;

    /**
     * 持卡人身份证号
     */
    @TableField("id_no")
    private String idNo;

    /**
     * 银行卡类型 1-储蓄卡;2-信用卡
     */
    private Integer type;

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
