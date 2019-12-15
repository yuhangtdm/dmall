package com.dmall.mms;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description: 会员服务启动类
 * @author: created by hang.yu on 2019/10/15 22:33
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableApolloConfig
@EnableTransactionManagement
@MapperScan(basePackages = "com.dmall.mms.generator.mapper")
public class MemberApplication {

    public static void main(String[] args) {
        // 避免es启动抱错
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(MemberApplication.class,args);
    }

}
