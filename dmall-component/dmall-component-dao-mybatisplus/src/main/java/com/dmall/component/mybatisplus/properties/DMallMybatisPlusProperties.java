package com.dmall.component.mybatisplus.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: dmall的mybatisPlus配置类
 * @author: created by hang.yu on 2019/11/2 16:50
 */
@ConfigurationProperties(prefix = "dmall.mybatisplus")
@Data
public class DMallMybatisPlusProperties {

    /**
     * 是否生效
     */
    private Boolean enabled = Boolean.TRUE;

    /**
     * 性能分析拦截器 是否使用
     */
    private Boolean performance = Boolean.TRUE;

    /**
     * 性能分析的最大执行时间 100ms
     */
    private Long maxTime = 100L;

    /**
     * sql是否格式化
     */
    private Boolean format = Boolean.TRUE;

    /**
     * 新增时间的列名
     */
    private String createTimeColumn = "gmtCreated";

    /**
     * 更新时间的列名
     */
    private String updateTimeColumn = "gmtModified";

    /**
     * 逻辑删除的列名
     */
    private String isDeletedColumn = "isDeleted";

}
