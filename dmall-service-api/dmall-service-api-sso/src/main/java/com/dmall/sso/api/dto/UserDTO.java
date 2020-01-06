package com.dmall.sso.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 缓存用户实体
 * @author: created by hang.yu on 2020/1/6 22:23
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 8813933971551287235L;

    /**
     * id
     */
    private Long id;

    /**
     * 商家店铺id
     */
    private Long merchantsId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 真实姓名
     */
    private String realName;

}