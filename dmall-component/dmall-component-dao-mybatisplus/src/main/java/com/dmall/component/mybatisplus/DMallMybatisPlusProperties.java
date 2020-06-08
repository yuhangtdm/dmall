package com.dmall.component.mybatisplus;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: dmall的mybatisPlus配置类
 * @author: created by hang.yu on 2019/11/2 16:50
 */
@Data
@ConfigurationProperties(prefix = "dmall.mybatisplus")
public class DMallMybatisPlusProperties {

    /**
     * 是否生效,默认不生效
     */
    private Boolean enabled = Boolean.FALSE;

    /**
     * 性能分析拦截器 是否使用
     */
    private Boolean performance = Boolean.FALSE;

    /**
     * 性能分析的最大执行时间 10000ms
     */
    private Long maxTime = 10000L;

    /**
     * sql是否格式化
     */
    private Boolean format = Boolean.TRUE;

}
