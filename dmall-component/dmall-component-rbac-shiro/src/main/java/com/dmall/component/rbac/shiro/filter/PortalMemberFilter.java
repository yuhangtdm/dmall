package com.dmall.component.rbac.shiro.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.dmall.common.constants.Constants;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResponseUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.AdminLoginProperties;
import com.dmall.component.rbac.shiro.PortalLoginProperties;
import com.dmall.component.rbac.shiro.util.CommonFilterUtil;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 前台认证过滤器
 * @author: created by hang.yu on 2020/1/6 23:54
 */
public class PortalMemberFilter extends PathMatchingFilter {

    private PortalLoginProperties portalLoginProperties;

    public PortalMemberFilter(PortalLoginProperties portalLoginProperties) {
        this.portalLoginProperties = portalLoginProperties;
    }

    @Override
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean filter = CommonFilterUtil.portalFilter(request, portalLoginProperties);
        if (filter) {
            return filter;
        }

        String userDto = request.getHeader(Constants.PORTAL_MEMBER);
        if (StrUtil.isBlank(userDto)) {
            ResponseUtil.writeJson(response, ResultUtil.fail(BasicStatusEnum.USER_NOT_LOGIN));
            return false;
        }
        PortalMemberDTO portalMemberDTO = JSON.parseObject(URLUtil.decode(userDto), PortalMemberDTO.class);
        PortalMemberContextHolder.set(portalMemberDTO);
        return true;
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        PortalMemberContextHolder.remove();
    }
}
