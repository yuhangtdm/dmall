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
 * 会员表
 * </p>
 *
 * @author yuhang
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mms_member")
public class MemberDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员用户名 可登录,必须唯一
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 昵称 可以重复
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别 1-男;2-女;3-保密
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 手机号 可登录
     */
    private String phone;

    /**
     * 邮箱 可登录
     */
    private String email;

    /**
     * 婚姻状况 1-未婚;2-已婚;3-保密
     */
    @TableField("marital_status")
    private Integer maritalStatus;

    /**
     * 月收入 范围
     */
    @TableField("monthly_income")
    private Integer monthlyIncome;

    /**
     * 身份证号码
     */
    @TableField("id_card_number")
    private String idCardNumber;

    /**
     * 教育程度 数据字典
     */
    @TableField("education_degree")
    private Integer educationDegree;

    /**
     * 所在行业 数据字典
     */
    private Integer industry;

    /**
     * 头像
     */
    private String icon;

    /**
     * 用户来源
     */
    @TableField("source_type")
    private Integer sourceType;

    /**
     * 积分
     */
    private Integer integration;

    /**
     * 成长值
     */
    private Integer growth;

    /**
     * 历史积分数量
     */
    @TableField("history_integration")
    private Integer historyIntegration;

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
