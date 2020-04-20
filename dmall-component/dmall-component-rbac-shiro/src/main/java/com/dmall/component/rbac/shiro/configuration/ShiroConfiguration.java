package com.dmall.component.rbac.shiro.configuration;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.model.BasicConfiguration;
import com.dmall.common.util.JsonUtil;
import com.dmall.component.rbac.shiro.AdminProperties;
import com.dmall.component.rbac.shiro.PortalProperties;
import com.dmall.component.rbac.shiro.feign.AdminPermissionFeign;
import com.dmall.component.rbac.shiro.filter.AdminPermissionFilter;
import com.dmall.component.rbac.shiro.filter.AdminUserFilter;
import com.dmall.component.rbac.shiro.filter.PortalMemberFilter;
import com.google.common.collect.Maps;
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
@EnableConfigurationProperties({AdminProperties.class, PortalProperties.class})
@ComponentScan(basePackages = {"com.dmall.component.rbac.shiro.util"})
public class ShiroConfiguration implements BasicConfiguration {

    private static final String PERMISSION = "permission";

    private static final String ADMIN_USER = "adminUser";

    private static final String PORTAL_MEMBER = "portalMember";

    private static final String FILTER_PATH = StrUtil.format("{},{},{}", ADMIN_USER, PERMISSION, PORTAL_MEMBER);

    private static final String PATH = "/**";

    @Autowired
    private AdminProperties adminProperties;

    @Autowired
    private PortalProperties portalProperties;

    @Autowired
    private AdminPermissionFeign adminPermissionFeign;

    @Override
    @PostConstruct
    public void check() {
        log.info("init -> [{}],content:\n{}", "adminProperties", JsonUtil.toJsonPretty(adminProperties));
        log.info("init -> [{}],content:\n{}", "portalProperties", JsonUtil.toJsonPretty(portalProperties));
    }

    @Bean
    public SecurityManager securityManager() {
        return new DefaultWebSecurityManager();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //自定义过滤器
        Map<String, Filter> customisedFilter = Maps.newHashMap();
        customisedFilter.put(ADMIN_USER, adminUserFilter());
        customisedFilter.put(PERMISSION, adminPermissionFilter());
        customisedFilter.put(PORTAL_MEMBER, portalMemberFilter());

        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        filterChainDefinitionMap.put(PATH, FILTER_PATH);
        shiroFilterFactoryBean.setFilters(customisedFilter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 后台权限过滤器
     */
    private AdminPermissionFilter adminPermissionFilter() {
        return new AdminPermissionFilter(adminProperties, adminPermissionFeign);
    }

    /**
     * 后台用户过滤器
     */
    private AdminUserFilter adminUserFilter() {
        return new AdminUserFilter(adminProperties);
    }

    /**
     * 商城会员过滤器
     */
    private PortalMemberFilter portalMemberFilter() {
        return new PortalMemberFilter(portalProperties);
    }

}
