package com.dmall.component.rbac.shiro.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.JsonUtil;
import com.dmall.common.util.RequestMappingUtil;
import com.dmall.common.util.ResponseUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.AdminProperties;
import com.dmall.component.rbac.shiro.util.CommonFilterUtil;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 后台认证过滤器
 * @author: created by hang.yu on 2020/1/6 23:54
 */
public class AdminUserFilter extends PathMatchingFilter {

    private final AdminProperties adminProperties;

    public AdminUserFilter(AdminProperties adminProperties) {
        this.adminProperties = adminProperties;
    }

    @Override
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestMapping = RequestMappingUtil.getValue(request);
        boolean filter = CommonFilterUtil.adminFilter(request, requestMapping, adminProperties);
        if (filter) {
            return true;
        }

        String userDto = request.getHeader(Constants.ADMIN_USER);
        if (StrUtil.isBlank(userDto)) {
            ResponseUtil.writeJson(response, ResultUtil.fail(BasicStatusEnum.USER_NOT_LOGIN));
            return false;
        }
        AdminUserDTO adminUserDTO = JsonUtil.fromJson(URLUtil.decode(userDto), AdminUserDTO.class);
        if (adminUserDTO != null) {
            adminUserDTO.setSource(request.getHeader(Constants.SOURCE));
            AdminUserContextHolder.set(adminUserDTO);
        }
        return true;
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) {
        AdminUserContextHolder.remove();
    }
}
