package com.dmall.framework.zuul.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @description: ZuulSwaggerProperties
 * @author: created by hang.yu on 2019/12/30 23:21
 */
@Data
@ConfigurationProperties(prefix = "zuul.swagger")
public class ZuulSwaggerProperties {

    /**
     * 无需swagger的配置 zuul配置
     */
    private List<String> ignoreProjects;

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 免登录白名单
     */
    private List<String> whiteList;

    private String loginUrl;

}
