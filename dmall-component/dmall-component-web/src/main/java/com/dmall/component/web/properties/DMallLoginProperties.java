package com.dmall.component.web.properties;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @description: DMallLoginProperties
 * @author: created by hang.yu on 2020/1/9 23:28
 */
@Data
@ConfigurationProperties(prefix = "dmall.login")
public class DMallLoginProperties {

    /**
     * 白名单
     */
    private List<String> whiteList = Lists.newArrayList();

}
