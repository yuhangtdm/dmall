package com.dmall.component.web.filter;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.enums.base.BasicStatusEnum;
import com.dmall.component.web.util.ResultUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: created by hang.yu on 2020/1/6 23:54
 */
public class AdminUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userDto = request.getHeader(Constants.ADMIN_USER);
        if (StrUtil.isBlank(userDto)) {
            ResultUtil.fail(BasicStatusEnum.USER_NOT_LOGIN);
            //TODO 拦截后台的请求
        }
    }
}
