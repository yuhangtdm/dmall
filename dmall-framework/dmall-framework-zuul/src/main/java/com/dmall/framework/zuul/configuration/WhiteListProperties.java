package com.dmall.framework.zuul.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @description: 白名单配置类
 * @author: created by hang.yu on 2020/2/26 16:21
 */
@Data
@ConfigurationProperties(prefix = "zuul.white.list")
public class WhiteListProperties {

    /**
     * 后台免登录白名单
     */
    private List<String> admin;

    /**
     * 后台登录地址
     */
    private String adminLoginUrl;

    /**
     * 商城免登录白名单
     */
    private List<String> portal;

    /**
     * 前台登录地址
     */
    private String portalLoginUrl;
}
