package com.dmall.component.rbac.shiro.interceptor;

import com.dmall.common.constants.Constants;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.JsonUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @description: feign拦截器 设置用户信息到Header中
 * @author: created by hang.yu on 2020/1/7 22:41
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 从应用上下文中取出user信息，放入Feign的请求头中
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        if (adminUserDTO != null) {
            String userJson = JsonUtil.toJson(adminUserDTO);
            requestTemplate.header(Constants.ADMIN_USER, userJson);
            requestTemplate.header(Constants.SOURCE, adminUserDTO.getSource());
        }
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (portalMemberDTO != null) {
            String userJson = JsonUtil.toJson(portalMemberDTO);
            requestTemplate.header(Constants.PORTAL_MEMBER, userJson);
            requestTemplate.header(Constants.SOURCE, portalMemberDTO.getSource());
        }
    }
}
