package com.dmall.framework.zuul;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @description: 网关启动类
 * 访问路由规则 : API网关地址+访问的服务名称+接口URI
 * @author: created by hang.yu on 2019/10/16 20:35
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableApolloConfig
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
