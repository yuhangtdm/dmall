package com.dmall.framework.springboot.admin;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: springbootAdmin启动类
 * @author: created by hang.yu on 2019/11/15 23:28
 */
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
@EnableApolloConfig
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
