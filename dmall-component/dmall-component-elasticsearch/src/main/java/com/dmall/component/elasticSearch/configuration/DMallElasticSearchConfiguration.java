package com.dmall.component.elasticSearch.configuration;

import com.dmall.component.elasticSearch.ESDao;
import com.dmall.component.elasticSearch.properties.DMallElasticSearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: created by yuhang on 2019/11/6 22:58
 */
@Configuration
@EnableConfigurationProperties(DMallElasticSearchProperties.class)
public class DMallElasticSearchConfiguration {


    @Bean
    public ESDao esDao(){
        return new ESDao();
    }

}
