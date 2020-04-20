package com.dmall.component.rbac.shiro.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.enums.SourceEnum;
import com.dmall.common.util.RequestMappingUtil;
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
    public static boolean adminFilter(HttpServletRequest request, AdminProperties adminProperties) throws Exception {
        String header = request.getHeader(Constants.SOURCE);
        if (StrUtil.isBlank(header)) {
            return false;
        }
        // 后台管理系统才做拦截
        if (!SourceEnum.ADMIN.getCode().equals(header)) {
            return true;
        }
        // 未配置白名单 或白名单内的URL有效
        return CollUtil.isEmpty(adminProperties.getWhiteList())
                || adminProperties.getWhiteList().contains(RequestMappingUtil.getValue(request));
    }

    public static boolean portalFilter(HttpServletRequest request, PortalProperties portalProperties) throws Exception {
        String header = request.getHeader(Constants.SOURCE);
        if (StrUtil.isBlank(header)) {
            return false;
        }
        // 前台系统才做拦截
        if (!SourceEnum.PORTAL.getCode().equals(header)) {
            return true;
        }
        return CollUtil.isEmpty(portalProperties.getWhiteList())
                || portalProperties.getWhiteList().contains(RequestMappingUtil.getValue(request));
    }

}
