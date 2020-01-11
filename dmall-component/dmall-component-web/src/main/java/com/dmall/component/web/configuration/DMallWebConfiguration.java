package com.dmall.component.web.configuration;

import com.dmall.component.web.interceptor.AdminUserInterceptor;
import com.dmall.component.web.properties.DMallLoginProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: web配置
 * @author: created by hang.yu on 2019/10/16 22:28
 */
@Configuration
@EnableConfigurationProperties({DMallLoginProperties.class})
@ComponentScan(basePackages = {"com.dmall.component.web.exceptionhandler",
        "com.dmall.component.web.log",
        "com.dmall.component.web.util"})
public class DMallWebConfiguration implements WebMvcConfigurer {

    @Autowired
    private DMallLoginProperties dMallLoginProperties;

    /**
     * 添加后台拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminUserInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(dMallLoginProperties.getWhiteList());

    }
}
