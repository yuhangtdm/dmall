package com.dmall.component.activemq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: 缓存属性配置
 * @author: created by hang.yu on 2019/11/3 21:50
 */
@Data
@ConfigurationProperties(prefix = "dmall.activemq")
public class DMallActiveMQProperties {

    /**
     * 是否可用
     */
    private Boolean enabled = Boolean.FALSE;

    /**
     * 使用主题
     */
    private Boolean useTopic = Boolean.FALSE;

    /**
     * 使用队列
     */
    private Boolean useQueue = Boolean.FALSE;

    /**
     * 默认的主题名称
     */
    private String defaultTopic;

    /**
     * 默认的队列名称
     */
    private String defaultQueue;


}
