package com.dmall.component.rbac.shiro.filter;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.AjaxUtil;
import com.dmall.common.util.ResponseUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.AdminLoginProperties;
import com.dmall.component.rbac.shiro.feign.AdminPermissionFeign;
import com.dmall.component.rbac.shiro.util.CommonFilterUtil;
import com.dmall.component.rbac.shiro.util.PathUtil;
import com.dmall.sso.api.dto.admin.PermissionResponseDTO;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description: 后台权限过滤器
 * @author: created by hang.yu on 2020/1/12 10:59
 */
public class AdminPermissionFilter extends PathMatchingFilter {

    private AdminLoginProperties adminLoginProperties;

    private AdminPermissionFeign adminPermissionFeign;

    public AdminPermissionFilter(AdminLoginProperties adminLoginProperties, AdminPermissionFeign adminPermissionFeign) {
        this.adminLoginProperties = adminLoginProperties;
        this.adminPermissionFeign = adminPermissionFeign;
    }

    @Override
    protected boolean onPreHandle(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue)
            throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean filter = CommonFilterUtil.adminFilter(request, adminLoginProperties);
        if (filter) {
            return true;
        }
        String requestMapping = PathUtil.getRequestMapping(request);
        // 非RequestMapping的url
        if (requestMapping == null){
            return true;
        }
        // 调用sso获取权限数据
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        BaseResult<List<PermissionResponseDTO>> listBaseResult = adminPermissionFeign.listPermissions(adminUserDTO.getUserName());
        List<PermissionResponseDTO> permissionList = listBaseResult.getData();
        String method = request.getMethod();
        for (PermissionResponseDTO permission : permissionList) {
            if (method.equalsIgnoreCase(permission.getMethod()) && requestMapping.equals(permission.getUri())) {
                return true;
            }
        }
        if (AjaxUtil.isAjax(request)) {
            ResponseUtil.writeJson(response, ResultUtil.fail(BasicStatusEnum.USER_NOT_ALLOW));
        } else {
            // 重定向到未授权地址
            response.sendRedirect(adminLoginProperties.getUnauthorizedUrl());
        }
        return false;
    }

}
