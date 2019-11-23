package com.dmall.portal;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: created by hang.yu on 2019/11/16 10:07
 */
@SpringBootApplication
@NacosPropertySource(dataId = "application",groupId = "dmall-web-portal",autoRefreshed = true)
public class WebPortalApp {

    public static void main(String[] args) {
        SpringApplication.run(WebPortalApp.class, args);
    }

}
