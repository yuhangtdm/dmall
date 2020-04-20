package com.dmall.common.util;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * @description: 获取@RequestMapping的映射
 * @author: created by hang.yu on 2020/2/26 16:49
 */
public class RequestMappingUtil {

    /**
     * 获取RequestMapping的url
     */
    public static String getValue(HttpServletRequest request) throws Exception {
        RequestMappingHandlerMapping mapping = SpringUtil.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        HandlerExecutionChain handler = mapping.getHandler(request);
        HandlerMethod handlerMethod = (HandlerMethod) handler.getHandler();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo k = entry.getKey();
            HandlerMethod v = entry.getValue();
            if (handlerMethod.getMethod().equals(v.getMethod())) {
                Set<String> patterns = k.getPatternsCondition().getPatterns();
                if (!patterns.isEmpty()) {
                    return patterns.iterator().next();
                }
            }
        }
        return null;
    }
}
