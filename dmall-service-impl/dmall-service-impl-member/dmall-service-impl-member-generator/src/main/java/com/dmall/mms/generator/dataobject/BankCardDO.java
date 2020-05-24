package com.dmall.mms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 会员银行卡表
 * @author: created by hang.yu on 2020-02-23 19:42:03
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
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String cardNo;

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
    private String idNo;

    /**
     * 银行卡类型 1-储蓄卡;2-信用卡
     */
    private Integer type;

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
