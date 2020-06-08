package com.dmall.component.elasticsearch;

import com.dmall.common.model.BasicConfiguration;
import com.dmall.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @description: ElasticSearch配置类
 * @author: created by hang.yu on 2019/11/6 22:58
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DMallElasticSearchProperties.class)
public class DMallElasticSearchConfiguration implements BasicConfiguration {

    @Autowired
    private DMallElasticSearchProperties dMallElasticSearchProperties;

    @Override
    @PostConstruct
    public void check() {
        log.info("init -> [{}],properties:\n{}", "DMallElasticSearchProperties",
            JsonUtil.toJsonPretty(dMallElasticSearchProperties));
    }

    @Bean
    public ESDao esDao() {
        return new ESDao();
    }

}
