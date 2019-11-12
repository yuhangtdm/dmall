package com.dmall.web.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: created by yuhang on 2019/11/10 16:33
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
//@EnableApolloConfig
public class WebAdminApp {

    public static void main(String[] args) {
        SpringApplication.run(WebAdminApp.class,args);
    }

}
