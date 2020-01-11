package com.dmall.component.mybatisplus.configuration;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.dmall.common.model.BasicConfiguration;
import com.dmall.component.mybatisplus.autofill.AutoFillHandler;
import com.dmall.component.mybatisplus.properties.DMallMybatisPlusProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

/**
 * @description: dmall的mybatisPlus配置类
 * @author: created by hang.yu on 2019/11/2 16:50
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({DMallMybatisPlusProperties.class})
@EnableTransactionManagement
@ConditionalOnProperty(prefix = "dmall.mybatisplus", value = "enabled", havingValue = "true")
public class DMallMybatisPlusConfiguration implements BasicConfiguration {

    @Autowired
    private DMallMybatisPlusProperties dmallMybatisPlusProperties;

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 性能分析拦截器，不建议生产使用
     */
    @Bean
    @ConditionalOnProperty(prefix = "dmall.mybatisplus", value = "performance", havingValue = "true")
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(dmallMybatisPlusProperties.getMaxTime());
        performanceInterceptor.setFormat(dmallMybatisPlusProperties.getFormat());
        return performanceInterceptor;
    }

    /**
     * 时间的自动填充处理类
     */
    @Bean
    public AutoFillHandler autoFillHandler() {
        return new AutoFillHandler(dmallMybatisPlusProperties);
    }

    @Override
    @PostConstruct
    public void check() {
        log.info("init -> [{}],properties:\n{}", "DMallMybatisPlusProperties", JSON.toJSONString(dmallMybatisPlusProperties, true));
    }

}
