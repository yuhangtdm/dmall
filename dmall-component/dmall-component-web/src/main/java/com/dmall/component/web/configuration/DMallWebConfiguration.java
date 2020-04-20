package com.dmall.component.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: web配置
 * @author: created by hang.yu on 2019/10/16 22:28
 */
@Configuration
@ComponentScan(basePackages = {"com.dmall.component.web"})
public class DMallWebConfiguration implements WebMvcConfigurer {

}
