package com.dmall.component.rbac.shiro.util;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.enums.SourceEnum;
import com.dmall.component.rbac.shiro.AdminProperties;
import com.dmall.component.rbac.shiro.PortalProperties;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: CommonFilter
 * @author: created by hang.yu on 2020/1/13 22:08
 */
public class CommonFilterUtil {

    /**
     * 后台系统过滤
     */
    public static boolean adminFilter(HttpServletRequest request, String requestMapping, AdminProperties adminProperties) {
        String header = request.getHeader(Constants.SOURCE);
        if (StrUtil.isBlank(header)) {
            return false;
        }
        // 后台管理系统才做拦截
        if (!SourceEnum.ADMIN.getCode().equals(header)) {
            return true;
        }
        return adminProperties.getWhiteList().contains(requestMapping);
    }

    public static boolean portalFilter(HttpServletRequest request, String requestMapping, PortalProperties portalProperties) {
        String header = request.getHeader(Constants.SOURCE);
        if (StrUtil.isBlank(header)) {
            return false;
        }
        // 前台系统才做拦截
        if (!SourceEnum.PORTAL.getCode().equals(header)) {
            return true;
        }
        return portalProperties.getWhiteList().contains(requestMapping);
    }

}
