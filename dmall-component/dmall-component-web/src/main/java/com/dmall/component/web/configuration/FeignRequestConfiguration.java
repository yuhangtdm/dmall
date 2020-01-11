package com.dmall.component.web.configuration;

import com.dmall.component.web.interceptor.FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @description: feign配置类
 * @author: created by hang.yu on 2020/1/7 22:57
 */
//@Configuration
public class FeignRequestConfiguration {

    /**
     * feign拦截器
     */
    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
