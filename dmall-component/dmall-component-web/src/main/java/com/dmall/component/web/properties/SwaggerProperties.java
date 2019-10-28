package com.dmall.component.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @description: swagger相关配置
 * @author: created by yuhang on 2019/10/27 16:12
 */
@ConfigurationProperties(prefix = "swagger")
@Data
public class SwaggerProperties {

    /**
     * 是否开启swagger，默认开启
     */
    private Boolean enabled = true;

    /**
     * swagger默认扫描的包路径
     */
    private String basePackage;

    /**
     * 标题
     */
    private String title;

    /**
     * 详细信息
     */
    private String description;

    /**
     * 请求路径
     */
    private String serviceUrl;

    /**
     * 版本号
     */
    private String version;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人地址
     */
    private String contactUrl;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

    /**
     * 无需swagger的配置
     */
    private List<String> ignoreProjects;
}
