package com.dmall.common.model.portal;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 缓存商城用户实体
 * @author: created by hang.yu on 2020/1/6 22:23
 */
@Data
public class PortalMemberDTO implements Serializable {

    private static final long serialVersionUID = 8813933971551287235L;

    /**
     * id
     */
    private Long id;

    /**
     * 会员名
     */
    private String memberName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 来源
     */
    private String source;

    /**
     * 微信号
     */
    private String wechatNo;

    /**
     * 头像
     */
    private String icon;

    /**
     * 密码
     */
    private String password;

    /**
     * token
     */
    private String token;

}