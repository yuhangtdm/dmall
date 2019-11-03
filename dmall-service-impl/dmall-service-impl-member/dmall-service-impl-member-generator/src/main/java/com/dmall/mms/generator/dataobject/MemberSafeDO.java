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
 * 账户安全表
 * </p>
 *
 * @author yuhang
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mms_member_safe")
public class MemberSafeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 是否绑定手机号 Y-可用;N-不可用
     */
    @TableField("bind_mobile")
    private String bindMobile;

    /**
     * 是否设置密码 Y-可用;N-不可用
     */
    @TableField("set_password")
    private String setPassword;

    /**
     * 是否绑定邮箱 Y-可用;N-不可用
     */
    @TableField("bind_email")
    private String bindEmail;

    /**
     * 是否实名认证 Y-可用;N-不可用
     */
    @TableField("real_name_authentication")
    private String realNameAuthentication;

    /**
     * 是否锁定账号 Y-可用;N-不可用
     */
    @TableField("lock_account")
    private String lockAccount;

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
     * 状态 Y-可用;N-不可用
     */
    @TableField("is_deleted")
    private String isDeleted;


}
