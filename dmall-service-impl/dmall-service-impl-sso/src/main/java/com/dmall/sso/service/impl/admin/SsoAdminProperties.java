package com.dmall.sso.service.impl.admin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;

/**
 * @description: SsoAdminProperties
 * @author: created by hang.yu on 2020/1/9 23:28
 */
@Data
@ConfigurationProperties(prefix = "dmall.sso.admin.shiro")
public class SsoAdminProperties {

    /**
     * 登录成功后跳转的url
     */
    private String successUrl;

}
