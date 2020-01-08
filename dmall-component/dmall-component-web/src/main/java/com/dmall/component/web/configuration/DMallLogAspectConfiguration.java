package com.dmall.component.web.configuration;

import com.alibaba.fastjson.JSON;
import com.dmall.common.model.configuration.BasicConfiguration;
import com.dmall.component.web.exception.WebErrorEnum;
import com.dmall.component.web.exception.WebException;
import com.dmall.component.web.log.LogAdvice;
import com.dmall.component.web.properties.DMallLogProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * @description: 日志切面配置类
 * @author: created by hang.yu on 2019/11/10 18:47
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({DMallLogProperties.class})
@ConditionalOnProperty(prefix = "dmall.web.log", value = "enabled", havingValue = "true")
public class DMallLogAspectConfiguration implements BasicConfiguration {

    @Autowired
    private DMallLogProperties dMallLogProperties;

    @Autowired
    private Environment environment;

    @Bean
    public AspectJExpressionPointcutAdvisor configurabledvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        String expression = "@within(org.springframework.web.bind.annotation.RestController) " +
                "|| @within(org.springframework.stereotype.Controller) " +
                "&& execution(public * " + dMallLogProperties.getPointcut() + "..*(..))";
        advisor.setExpression(expression);
        advisor.setAdvice(logAdvice(environment));
        return advisor;
    }

    @Bean
    public LogAdvice logAdvice(Environment environment) {
        return new LogAdvice(environment);

    }

    @Override
    @PostConstruct
    public void check() {
        log.info("init -> [{}],properties:\n{}", "DMallLogProperties", JSON.toJSONString(dMallLogProperties, true));
        if (dMallLogProperties.getEnabled() && StringUtils.isBlank(dMallLogProperties.getPointcut())) {
            throw new WebException(WebErrorEnum.NO_POINTCUT);
        }
    }

}
