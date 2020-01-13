package com.dmall.component.rbac.shiro.filter;

import com.dmall.common.constants.Constants;
import com.dmall.common.enums.SourceEnum;
import com.dmall.component.rbac.shiro.ShiroProperties;
import com.dmall.component.rbac.shiro.util.SpringUtil;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: CommonFilter
 * @author: created by hang.yu on 2020/1/13 22:08
 */
public class CommonFilter {

    public static boolean filter(HttpServletRequest request, ShiroProperties shiroProperties) throws Exception{
        String header = request.getHeader(Constants.SOURCE);
        // 后台管理系统才做拦截
        if (!SourceEnum.ADMIN.getCode().equals(header)) {
            return true;
        }
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        // todo 获取 正确的url
        String requestMapping = urlPathHelper.getLookupPathForRequest(request);
        if (shiroProperties.getWhiteList().contains(requestMapping)) {
            return true;
        }
        return false;
    }
}
