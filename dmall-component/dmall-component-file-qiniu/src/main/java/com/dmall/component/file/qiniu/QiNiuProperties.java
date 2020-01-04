package com.dmall.component.file.qiniu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 10:16
 */
@Data
@ConfigurationProperties(prefix = "dmall.file.qiniu")
public class QiNiuProperties {

    /**
     * 是否生效 默认不开启
     */
    private Boolean enabled = false;

    /**
     * 公钥
     */
    private String accessKey;

    /**
     * 私钥
     */
    private String secretKey;

    /**
     * 空间名称
     */
    private String bucket;

    /**
     * 访问资源的域名
     */
    private String domain;

    /**
     * 资源的空间
     */
    private String namespace;
}
