package com.dmall.component.rbac.shiro.configuration;

import com.dmall.component.rbac.shiro.ShiroProperties;
import com.dmall.component.rbac.shiro.filter.AdminPermissionFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: Shiro配置
 * @author: created by hang.yu on 2020/1/12 10:53
 */
@Slf4j
@Configuration
//@EnableFeignClients/*(basePackages = {"com.dmall.component.rbac.shiro.feign"})*/
//@EnableConfigurationProperties(ShiroProperties.class)
//@ComponentScan(basePackages = {"com.dmall.component.rbac.shiro"})
public class ShiroConfiguration {

    private static final String PERMISSION = "permission";

    private static final String ADMIN_USER = "adminUser";

    private static final String PATH = "/**";

    @Autowired
    private ShiroProperties shiroProperties;

    @Bean
    public SecurityManager securityManager(){
        return new DefaultWebSecurityManager();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        log.info("开启shiro功能");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap();
        //自定义拦截器
        Map<String, Filter> customisedFilter = new HashMap<>();
//        customisedFilter.put(ADMIN_USER, adminUserFilter());
        customisedFilter.put(PERMISSION, adminPermissionFilter());
//        filterChainDefinitionMap.put(PATH, ADMIN_USER);
        filterChainDefinitionMap.put(PATH, PERMISSION);
        shiroFilterFactoryBean.setFilters(customisedFilter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    private AdminPermissionFilter adminPermissionFilter(){
        return new AdminPermissionFilter();
    }

//    private AdminUserFilter adminUserFilter(){ return new AdminUserFilter(shiroProperties);}
}
