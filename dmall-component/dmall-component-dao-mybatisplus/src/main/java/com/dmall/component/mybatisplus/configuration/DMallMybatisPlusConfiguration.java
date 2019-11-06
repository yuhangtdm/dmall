package com.dmall.component.mybatisplus.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.dmall.component.mybatisplus.properties.DMallMybatisPlusProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({DMallMybatisPlusProperties.class})
@ConditionalOnProperty(prefix = "dmall.mybatisplus", value = "enabled", havingValue = "true")
public class DMallMybatisPlusConfiguration {

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
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(dmallMybatisPlusProperties.getMaxTime());
        performanceInterceptor.setFormat(dmallMybatisPlusProperties.getFormat());
        return performanceInterceptor;
    }

   /*
    构建该bean时导致注入Properties对象为null
   @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.dmall.mms.generator.mapper");
        return scannerConfigurer;
    }*/

}
