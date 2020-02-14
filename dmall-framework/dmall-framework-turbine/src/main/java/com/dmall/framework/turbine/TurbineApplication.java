package com.dmall.framework.turbine;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @description: TurbineApplication
 * @author: created by hang.yu on 2020/2/14 17:22
 */
@EnableTurbine
@EnableApolloConfig
@EnableHystrixDashboard
@SpringBootApplication
public class TurbineApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}
