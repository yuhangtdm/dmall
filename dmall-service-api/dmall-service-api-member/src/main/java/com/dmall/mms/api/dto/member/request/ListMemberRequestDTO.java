package com.dmall.mms.api.dto.member.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 会员列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListMemberRequestDTO", description="会员列表请求实体")
public class ListMemberRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "会员用户名 可登录,必须唯一", position = 2)
    private String name;


    @ApiModelProperty(value = "密码", position = 3)
    private String password;


    @ApiModelProperty(value = "真实姓名", position = 4)
    private String realName;


    @ApiModelProperty(value = "昵称 可以重复", position = 5)
    private String nickName;


    @ApiModelProperty(value = "性别 1-男;2-女;3-保密", position = 6)
    private Integer gender;


    @ApiModelProperty(value = "生日", position = 7)
    private Date birthday;


    @ApiModelProperty(value = "手机号 可登录", position = 8)
    private String phone;


    @ApiModelProperty(value = "邮箱 可登录", position = 9)
    private String email;


    @ApiModelProperty(value = "婚姻状况 1-未婚;2-已婚;3-保密", position = 10)
    private Integer maritalStatus;


    @ApiModelProperty(value = "月收入 范围", position = 11)
    private Integer monthlyIncome;


    @ApiModelProperty(value = "身份证号码", position = 12)
    private String idCardNumber;


    @ApiModelProperty(value = "教育程度 数据字典", position = 13)
    private Integer educationDegree;


    @ApiModelProperty(value = "所在行业 数据字典", position = 14)
    private Integer industry;


    @ApiModelProperty(value = "头像", position = 15)
    private String icon;


    @ApiModelProperty(value = "用户来源", position = 16)
    private Integer sourceType;


    @ApiModelProperty(value = "积分", position = 17)
    private Integer integration;


    @ApiModelProperty(value = "成长值", position = 18)
    private Integer growth;


    @ApiModelProperty(value = "历史积分数量", position = 19)
    private Integer historyIntegration;












}
