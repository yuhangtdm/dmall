package com.dmall.component.web.configuration;

import com.dmall.component.web.filter.FeignRequestIntercptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: feign配置类
 * @author: created by hang.yu on 2020/1/7 22:57
 */
@Configuration
public class FeignRequestConfiguration {

    @Bean
    public FeignRequestIntercptor feignRequestIntercptor() {
        return new FeignRequestIntercptor();
    }
}
