package com.dmall.component.web.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.dmall.common.constants.Constants;
import com.dmall.common.enums.SourceEnum;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.user.AdminUserContextHolder;
import com.dmall.common.model.user.UserDTO;
import com.dmall.common.util.ResponseUtil;
import com.dmall.common.util.ResultUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * @description: 后台管理用户拦截器
 * 用来设置user到ThreadLocal中
 * @author: created by hang.yu on 2020/1/6 23:54
 */
public class AdminUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader(Constants.SOURCE);
        if (!SourceEnum.ADMIN.getCode().equals(header)) {
            return true;
        }
        String userDto = request.getHeader(Constants.ADMIN_USER);
        if (StrUtil.isBlank(userDto)) {
            ResponseUtil.writeJson(response, ResultUtil.fail(BasicStatusEnum.USER_NOT_LOGIN));
            return false;
        }
        UserDTO userDTO = JSON.parseObject(userDto, UserDTO.class);
        userDTO.setSource(header);
        AdminUserContextHolder.set(userDTO);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AdminUserContextHolder.remove();
    }

}
