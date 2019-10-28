package com.dmall.service.impl;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 会员服务启动类
 * @author: created by yuhang on 2019/10/15 22:33
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
//@EnableApolloConfig
public class MemberServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(MemberServiceApp.class,args);
    }


}
