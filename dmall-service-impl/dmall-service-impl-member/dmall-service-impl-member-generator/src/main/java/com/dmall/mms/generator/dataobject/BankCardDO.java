package com.dmall.mms.generator.dataobject;

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
 * @description: 会员银行卡表
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mms_bank_card")
public class BankCardDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 银行名称
     */
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
     * 状态 Y-可用;N-不可用
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private String isDeleted;


}
