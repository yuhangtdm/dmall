package com.dmall.component.rbac.shiro.filter;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.RequestMappingUtil;
import com.dmall.common.util.ResponseUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.AdminProperties;
import com.dmall.component.rbac.shiro.feign.AdminPermissionFeign;
import com.dmall.component.rbac.shiro.util.CommonFilterUtil;
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

    private final AdminProperties adminProperties;

    private final AdminPermissionFeign adminPermissionFeign;

    public AdminPermissionFilter(AdminProperties adminProperties, AdminPermissionFeign adminPermissionFeign) {
        this.adminProperties = adminProperties;
        this.adminPermissionFeign = adminPermissionFeign;
    }

    @Override
    protected boolean onPreHandle(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue)
            throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestMapping = RequestMappingUtil.getValue(request);
        boolean filter = CommonFilterUtil.adminAuthFilter(request, requestMapping,adminProperties);
        if (filter) {
            return true;
        }
        // 非RequestMapping的url
        if (requestMapping == null) {
            return true;
        }
        // 调用sso获取权限数据
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        BaseResult<List<PermissionResponseDTO>> listBaseResult = adminPermissionFeign.listPermissions(adminUserDTO.getPhone());
        List<PermissionResponseDTO> permissionList = listBaseResult.getData();
        String method = request.getMethod();
        for (PermissionResponseDTO permission : permissionList) {
            if (method.equalsIgnoreCase(permission.getMethod()) && requestMapping.equals(permission.getUri())) {
                return true;
            }
        }
        ResponseUtil.writeJson(response, ResultUtil.fail(BasicStatusEnum.USER_NOT_ALLOW));
        return false;
    }

}
