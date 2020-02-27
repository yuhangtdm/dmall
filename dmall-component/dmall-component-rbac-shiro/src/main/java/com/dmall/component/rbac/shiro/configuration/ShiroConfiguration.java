package com.dmall.component.rbac.shiro.configuration;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.dmall.common.model.BasicConfiguration;
import com.dmall.component.rbac.shiro.AdminLoginProperties;
import com.dmall.component.rbac.shiro.PortalLoginProperties;
import com.dmall.component.rbac.shiro.feign.AdminPermissionFeign;
import com.dmall.component.rbac.shiro.filter.AdminPermissionFilter;
import com.dmall.component.rbac.shiro.filter.AdminUserFilter;
import com.dmall.component.rbac.shiro.filter.PortalMemberFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
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
@EnableFeignClients(basePackages = {"com.dmall.component.rbac.shiro.feign"})
@EnableConfigurationProperties({AdminLoginProperties.class, PortalLoginProperties.class})
@ComponentScan(basePackages = {"com.dmall.component.rbac.shiro.util"})
public class ShiroConfiguration implements BasicConfiguration {

    private static final String PERMISSION = "permission";

    private static final String ADMIN_USER = "adminUser";

    private static final String PORTAL_MEMBER = "portalMember";

    private static final String FILTER_PATH = StrUtil.format("{},{},{}",ADMIN_USER, PERMISSION, PORTAL_MEMBER);

    private static final String PATH = "/**";

    @Autowired
    private AdminLoginProperties adminLoginProperties;

    @Autowired
    private PortalLoginProperties portalLoginProperties;

    @Autowired
    private AdminPermissionFeign adminPermissionFeign;

    @Bean
    public SecurityManager securityManager() {
        return new DefaultWebSecurityManager();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        //自定义拦截器
        Map<String, Filter> customisedFilter = new HashMap<>();
        customisedFilter.put(ADMIN_USER, adminUserFilter());
        customisedFilter.put(PERMISSION, adminPermissionFilter());
        customisedFilter.put(PORTAL_MEMBER, portalMemberFilter());
        filterChainDefinitionMap.put(PATH, FILTER_PATH);
        shiroFilterFactoryBean.setFilters(customisedFilter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    private AdminPermissionFilter adminPermissionFilter() {
        return new AdminPermissionFilter(adminLoginProperties, adminPermissionFeign);
    }

    private AdminUserFilter adminUserFilter() {
        return new AdminUserFilter(adminLoginProperties);
    }

    private PortalMemberFilter portalMemberFilter(){ return new PortalMemberFilter(portalLoginProperties); }

    @Override
    @PostConstruct
    public void check() {
        log.info("init -> [{}],properties:\n{}", "ShiroProperties", JSON.toJSONString(adminLoginProperties, true));
    }
}
