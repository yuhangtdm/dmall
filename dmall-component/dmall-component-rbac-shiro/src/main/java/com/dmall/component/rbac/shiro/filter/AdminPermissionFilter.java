package com.dmall.component.rbac.shiro.filter;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.enums.SourceEnum;
import com.dmall.common.model.user.AdminUserContextHolder;
import com.dmall.common.model.user.UserDTO;
import com.dmall.common.util.AjaxUtil;
import com.dmall.common.util.ResponseUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.util.SpringUtil;
import com.dmall.component.rbac.shiro.feign.AdminPermissionFeign;
import com.dmall.sso.api.dto.PermissionResponseDTO;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 权限过滤器
 * @author: created by hang.yu on 2020/1/12 10:59
 */
public class AdminPermissionFilter extends PathMatchingFilter {

    @Override
    protected boolean onPreHandle(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue)
        throws Exception{

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String header = request.getHeader(Constants.SOURCE);
        // 后台管理系统才做拦截
        if (!SourceEnum.ADMIN.getCode().equals(header)) {
            return true;
        }
        // 调用sso获取权限数据
        AdminPermissionFeign adminPermissionFeign = SpringUtil.getBean(AdminPermissionFeign.class);
        UserDTO userDTO = AdminUserContextHolder.get();
        BaseResult<List<PermissionResponseDTO>> listBaseResult = adminPermissionFeign.listPermissions(userDTO.getUserName());
        List<PermissionResponseDTO> permissionList = listBaseResult.getData();
        permissionList = permissionList.stream().filter(permissionResponse -> StrUtil.isNotBlank(permissionResponse.getUri()))
                .collect(Collectors.toList());

        String method = request.getMethod();
        String uri = request.getRequestURI();
        for (PermissionResponseDTO permission : permissionList) {
            if (method.equalsIgnoreCase(permission.getMethod()) && matchs(uri, permission.getUri())) {
                return true;
            }
        }
        if (AjaxUtil.isAjax(request)){
            ResponseUtil.writeJson(response, ResultUtil.fail(BasicStatusEnum.USER_NOT_ALLOW));
        }else {
            // 重定向到未授权地址
            response.sendRedirect("");
        }

        return false;
    }

    private boolean matchs(String requestUri, String permissionUri) {
        if (requestUri.equals(permissionUri)) {
            return true;
        }
        // todo  比较 /user/{id} 这种uri
        return false;
    }

}
