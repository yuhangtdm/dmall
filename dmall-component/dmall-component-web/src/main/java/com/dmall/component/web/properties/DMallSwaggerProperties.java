package com.dmall.component.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: swagger属性类
 * @author: created by hang.yu on 2019/10/27 16:12
 */
@Data
@ConfigurationProperties(prefix = "dmall.web.swagger")
public class DMallSwaggerProperties {

    /**
     * 是否开启swagger，默认不开启
     */
    private Boolean enabled = false;

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

}
