package com.dmall.component.rbac.shiro.util;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.enums.SourceEnum;
import com.dmall.component.rbac.shiro.AdminLoginProperties;
import com.dmall.component.rbac.shiro.PortalLoginProperties;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: CommonFilter
 * @author: created by hang.yu on 2020/1/13 22:08
 */
public class CommonFilterUtil {

    public static boolean adminFilter(HttpServletRequest request, AdminLoginProperties adminLoginProperties) throws Exception {
        String header = request.getHeader(Constants.SOURCE);
        // 后台管理系统才做拦截
        if (!SourceEnum.ADMIN.getCode().equals(header)) {
            return true;
        }
        // 未配置白名单 或白名单内的URL有效
        return CollUtil.isEmpty(adminLoginProperties.getWhiteList())
                || adminLoginProperties.getWhiteList().contains(PathUtil.getRequestMapping(request));
    }

    public static boolean portalFilter(HttpServletRequest request, PortalLoginProperties portalLoginProperties) throws Exception {
        String header = request.getHeader(Constants.SOURCE);
        // 前台系统才做拦截
        if (!SourceEnum.PORTAL.getCode().equals(header)) {
            return true;
        }
        return CollUtil.isEmpty(portalLoginProperties.getWhiteList())
                || portalLoginProperties.getWhiteList().contains(PathUtil.getRequestMapping(request));
    }

}
