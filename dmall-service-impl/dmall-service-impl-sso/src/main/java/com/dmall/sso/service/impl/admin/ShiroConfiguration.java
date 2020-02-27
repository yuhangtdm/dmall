package com.dmall.sso.service.impl.admin;

import com.dmall.common.constants.Constants;
import com.dmall.sso.service.impl.SsoProperties;
import com.dmall.sso.service.impl.admin.shiro.AuthenticationRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: shiro配置
 * @author: created by hang.yu on 2020/1/11 16:41
 */
@Configuration
@EnableConfigurationProperties({SsoProperties.class})
public class ShiroConfiguration {

    /**
     * 加密方式和加密次数
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Constants.MD5);
        hashedCredentialsMatcher.setHashIterations(Constants.ENCRYPT_TIME);
        return hashedCredentialsMatcher;
    }

    @Bean
    public AuthenticationRealm shiroRealm(){
        AuthenticationRealm authenticationRealm = new AuthenticationRealm();
        authenticationRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return authenticationRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

}
