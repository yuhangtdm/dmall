package com.dmall.component.web.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 15:09
 */
@Data
@ConfigurationProperties(prefix = "dmall.web.httpclient" )
public class HttpClientProperties {

    /**
     * 是否启用 默认不开启
     */
    private Boolean enabled = false;

    /**
     * 最大连接数
     */
    private Integer maxTotal = 500;

    /**
     * 连接主机最大并发数
     */
    private Integer defaultMaxPerRoute = 100;

    /**
     * 创建连接的最长时间
     */
    private Integer connectTimeout = 20;

    /**
     * 从连接池中获取到连接的最长时间
     */
    private Integer connectionRequestTimeout = 50;

    /**
     * 数据传输的最长时间
     */
    private Integer socketTimeout = 60;

    /**
     * 空闲时间(用于定期清理空闲连接)
     */
    private Long maxIdleTime = 3000L;

    /**
     * 单位
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;
}
