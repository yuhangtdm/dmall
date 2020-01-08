package com.dmall.component.web.configuration;

import com.dmall.component.web.filter.AdminUserFilter;
import com.google.common.collect.Lists;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @description: 登录过滤器配置
 * @author: created by hang.yu on 2020/1/7 22:33
 */
//@Configuration
public class LoginFilterConfiguration {

    /**
     * 注册AdminUserFilter
     */
    @Bean
    public FilterRegistrationBean adminUserFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        AdminUserFilter adminUserFilter = new AdminUserFilter();
        registrationBean.setFilter(adminUserFilter);
        List<String> urlPatterns = Lists.newArrayList("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
