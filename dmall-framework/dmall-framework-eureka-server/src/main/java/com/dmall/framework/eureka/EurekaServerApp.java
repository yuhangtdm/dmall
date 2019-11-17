package com.dmall.framework.eureka;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @description: eureka服务端启动类
 * @author: created by yuhang on 2019/10/11 22:29
 */
@SpringBootApplication
@EnableEurekaServer
@EnableWebSecurity
@EnableApolloConfig
public class EurekaServerApp {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp.class, args);
    }
}
