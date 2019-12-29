package com.dmall.pms;

import com.dmall.common.model.service.CommonCacheService;
import com.dmall.component.web.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @description: CacheInitRunner
 * @author: created by hang.yu on 2019/12/29 19:17
 */
@Slf4j
@Component
public class CacheInitRunner implements CommandLineRunner {

    private static final String METHOD_NAME = "selectAll";

    @Override
    public void run(String... args) throws Exception {
        Map<String, CommonCacheService> beans = SpringUtil.getBeans(CommonCacheService.class);

        beans.forEach((k,bean) ->{
            Method method = ReflectionUtils.findMethod(bean.getClass(), METHOD_NAME);
            ReflectionUtils.invokeMethod(method, bean);
            log.info("execute bean:{}, selectAll method success",k);
        });
    }
}
