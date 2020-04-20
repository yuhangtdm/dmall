package com.dmall.component.file.qiniu;

import com.dmall.common.enums.component.QiNiuErrorEnum;
import com.dmall.common.model.BasicConfiguration;
import com.dmall.common.model.exception.ComponentException;
import com.dmall.common.util.JsonUtil;
import com.dmall.common.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @description: 七牛云配置类
 * @author: created by hang.yu on 2019/12/17 10:20
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({QiNiuProperties.class})
@ConditionalOnProperty(prefix = "dmall.file.qiniu", value = "enabled", havingValue = "true")
public class QiNiuConfiguration implements BasicConfiguration {

    @Autowired
    private QiNiuProperties qiNiuProperties;

    @Override
    @PostConstruct
    public void check() {
        log.info("init -> [{}],properties:\n{}", "QiNiuProperties", JsonUtil.toJsonPretty(qiNiuProperties));
        if (qiNiuProperties.getEnabled()) {
            if (ObjectUtil.containsEmpty(qiNiuProperties.getAccessKey(), qiNiuProperties.getSecretKey()
                    , qiNiuProperties.getBucket(), qiNiuProperties.getDomain())) {
                throw new ComponentException(QiNiuErrorEnum.QI_NIU_CONFIG_ERROR);
            }
        }
    }

    @Bean
    public QiNiuFileManager qiNiuFileManager() {
        return new QiNiuFileManager(qiNiuProperties);
    }

}
