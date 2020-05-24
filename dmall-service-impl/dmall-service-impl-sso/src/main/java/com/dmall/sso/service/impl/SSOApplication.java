package com.dmall.sso.service.impl;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 单点登录服务启动类
 * @author: created by hang.yu on 2020/1/5 16:52
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableApolloConfig
@MapperScan(basePackages = {"com.dmall.sso.service.impl.admin.mapper", "com.dmall.sso.service.impl.portal.mapper"})
@EnableConfigurationProperties({SsoProperties.class})
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class SSOApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class, args);
    }


}
