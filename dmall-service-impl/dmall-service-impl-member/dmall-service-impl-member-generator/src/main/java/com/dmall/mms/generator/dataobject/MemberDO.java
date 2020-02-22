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
 * @description: 会员表
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mms_member")
public class MemberDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员用户名 可登录,必须唯一
     */
    private String memberName;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 昵称 可以重复
     */
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
    private Integer maritalStatus;

    /**
     * 月收入 范围
     */
    private Integer monthlyIncome;

    /**
     * 身份证号码
     */
    private String idCardNumber;

    /**
     * 教育程度 数据字典
     */
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
     * 用户来源 1-注册;2-qq;3-微信
     */
    private Integer sourceType;

    /**
     * 积分
     */
    private Integer integration;

    /**
     * 等级
     */
    private String growth;

    /**
     * 历史积分
     */
    private Integer historyIntegration;

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
