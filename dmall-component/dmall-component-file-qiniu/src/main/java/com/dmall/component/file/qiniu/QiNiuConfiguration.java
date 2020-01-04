package com.dmall.component.file.qiniu;

import com.alibaba.fastjson.JSON;
import com.dmall.common.model.configuration.BasicConfiguration;
import com.dmall.common.util.ObjectUtil;
import com.dmall.component.file.qiniu.exception.QiNiuErrorEnum;
import com.dmall.component.file.qiniu.exception.QiNiuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 七牛云配置类
 * @author: created by hang.yu on 2019/12/17 10:20
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({QiNiuProperties.class})
@ConditionalOnProperty(prefix = "dmall.file.qiniu" , value = "enabled" , havingValue = "true" )
public class QiNiuConfiguration implements BasicConfiguration {

    @Autowired
    private QiNiuProperties qiNiuProperties;

    @Bean
    public QiNiuFileManager qiNiuFileManager() {
        return new QiNiuFileManager(qiNiuProperties);
    }

    @Override
    public void check() {
        log.info("init -> [{}],properties:\n{}" , "QiNiuProperties" , JSON.toJSONString(qiNiuProperties, true));
        if (qiNiuProperties.getEnabled()) {
            if (ObjectUtil.containsEmpty(qiNiuProperties.getAccessKey(), qiNiuProperties.getSecretKey()
                    , qiNiuProperties.getBucket(), qiNiuProperties.getDomain())) {
                throw new QiNiuException(QiNiuErrorEnum.QI_NIU_CONFIG_ERROR);
            }

        }
    }
}
