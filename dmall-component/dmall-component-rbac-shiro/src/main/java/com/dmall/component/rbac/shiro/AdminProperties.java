package com.dmall.component.rbac.shiro;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @description: 后台配置文件
 * @author: created by hang.yu on 2020/1/9 23:28
 */
@Data
@ConfigurationProperties(prefix = "dmall.rbac.admin")
public class AdminProperties {

    /**
     * 未授权地址
     */
    private String unauthorizedUrl;

    /**
     * 白名单
     */
    private List<String> whiteList = Lists.newArrayList();
}
