package com.dmall.sso.service.impl;

import com.dmall.component.web.configuration.DMallSwaggerConfiguration;
import com.dmall.sso.api.enums.SsoErrorEnum;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @description: 重写swagger配置 增加服务的返回状态
 * @author: created by hang.yu on 2020/4/20 22:40
 */
@Configuration
public class DMallSwaggerConfigurationImpl extends DMallSwaggerConfiguration {
    static {
        errorEnums.addAll(Arrays.asList(SsoErrorEnum.values()));
    }
}
