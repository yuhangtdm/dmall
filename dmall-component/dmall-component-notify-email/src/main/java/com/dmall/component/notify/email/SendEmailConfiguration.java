package com.dmall.component.notify.email;

import com.dmall.common.model.BasicConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import javax.annotation.PostConstruct;

/**
 * @description: SendEmailConfiguration
 * @author: created by hang.yu on 2019/11/6 22:58
 */
@Slf4j
@Configuration
public class SendEmailConfiguration implements BasicConfiguration {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.from}")
    private String from;

    @Bean
    public MailServiceImpl iMailService() {
        return new MailServiceImpl(javaMailSender, templateEngine, from);
    }


    @Override
    @PostConstruct
    public void check() {
        log.info("init send email successful,from:{}", from);
    }
}
