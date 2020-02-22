package com.dmall.component.rbac.shiro.util;

import com.dmall.common.constants.Constants;
import com.dmall.common.enums.SourceEnum;
import com.dmall.component.rbac.shiro.ShiroProperties;
import com.dmall.component.rbac.shiro.util.SpringUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * @description: CommonFilter
 * @author: created by hang.yu on 2020/1/13 22:08
 */
public class CommonFilterUtil {

    public static boolean filter(HttpServletRequest request, ShiroProperties shiroProperties) throws Exception {
        String header = request.getHeader(Constants.SOURCE);
        // 后台管理系统才做拦截
        if (!SourceEnum.ADMIN.getCode().equals(header)) {
            return true;
        }
        // 白名单不拦截
        return shiroProperties.getWhiteList().contains(getRequestMapping(request));
    }

    /**
     * 获取RequestMapping的url
     */
    public static String getRequestMapping(HttpServletRequest request) throws Exception {
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
