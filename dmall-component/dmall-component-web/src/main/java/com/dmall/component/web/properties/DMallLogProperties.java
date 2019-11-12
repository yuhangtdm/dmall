package com.dmall.component.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: created by yuhang on 2019/11/10 19:03
 */
@Data
@ConfigurationProperties(prefix = "dmall.web.log")
public class DMallLogProperties {

    /**
     * 是否开启log，默认开启
     */
    private Boolean enabled = false;

    /**
     * aop的pointcut 指定包扫描
     */
    private String pointcut;
}
