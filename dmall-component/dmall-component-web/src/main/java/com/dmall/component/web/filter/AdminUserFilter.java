package com.dmall.component.web.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.dmall.common.constants.Constants;
import com.dmall.common.enums.SourceEnum;
import com.dmall.common.enums.base.BasicStatusEnum;
import com.dmall.common.model.user.AdminUserContextHolder;
import com.dmall.common.model.user.UserDTO;
import com.dmall.component.web.util.ResultUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @description: AdminUserFilter
 * @author: created by hang.yu on 2020/1/6 23:54
 */
public class AdminUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String header = request.getHeader(Constants.SOURCE);
            if (!SourceEnum.ADMIN.getCode().equals(header)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            String userDto = request.getHeader(Constants.ADMIN_USER);
            if (StrUtil.isBlank(userDto)) {
                response.setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
                response.getWriter().write(JSON.toJSONString(ResultUtil.fail(BasicStatusEnum.USER_NOT_LOGIN)));
                return;
            }
            UserDTO userDTO = JSON.parseObject(userDto, UserDTO.class);
            userDTO.setSource(header);
            AdminUserContextHolder.set(userDTO);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            AdminUserContextHolder.remove();
        }
    }

}
