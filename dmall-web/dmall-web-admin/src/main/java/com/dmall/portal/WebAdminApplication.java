package com.dmall.portal;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 后台门户启动类
 * @author: created by hang.yu on 2020/1/5 12:16
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableApolloConfig
public class WebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class, args);
    }
}
