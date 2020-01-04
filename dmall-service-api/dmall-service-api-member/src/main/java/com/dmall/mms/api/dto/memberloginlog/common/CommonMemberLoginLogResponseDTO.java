package com.dmall.mms.api.dto.memberloginlog.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 会员登录记录公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonMemberLoginLogResponseDTO" , description = "会员登录记录公共响应实体" )
public class CommonMemberLoginLogResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id" , position = 1)
    private Long id;


    @ApiModelProperty(value = "member_id" , position = 2)
    private String memberId;


    @ApiModelProperty(value = "token" , position = 3)
    private String token;


    @ApiModelProperty(value = "ip地址" , position = 4)
    private String ip;


    @ApiModelProperty(value = "登录城市" , position = 5)
    private String city;


    @ApiModelProperty(value = "登录类型 1-PC;2-android;3-ios;4-小程序" , position = 6)
    private String type;


    @ApiModelProperty(value = "创建人" , position = 7)
    private Long creator;


    @ApiModelProperty(value = "创建时间" , position = 8)
    private Date gmtCreated;


    @ApiModelProperty(value = "更新人" , position = 9)
    private Long modifier;


    @ApiModelProperty(value = "更新时间" , position = 10)
    private Date gmtModified;


    @ApiModelProperty(value = "状态 Y-可用;N-不可用" , position = 11)
    private String isDeleted;


}
