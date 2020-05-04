package com.dmall.component.web.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: web配置
 * @author: created by hang.yu on 2019/10/16 22:28
 */
@Configuration
@ComponentScan(basePackages = {"com.dmall.component.web"})
public class DMallWebConfiguration implements WebMvcConfigurer {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
