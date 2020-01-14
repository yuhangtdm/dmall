package com.dmall.framework.zuul.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.dmall.common.constants.Constants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.SourceEnum;
import com.dmall.common.model.user.UserDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.framework.zuul.AdminUserErrorEnum;
import com.dmall.framework.zuul.AjaxUtil;
import com.dmall.framework.zuul.configuration.ZuulSwaggerProperties;
import com.dmall.framework.zuul.feign.LoginServiceFeign;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @description: 后台用户过滤器
 * @author: created by hang.yu on 2020/1/6 22:35
 */
@Component
public class AdminUserZuulFilter extends ZuulFilter {

    @Autowired
    private LoginServiceFeign loginServiceFeign;

    @Autowired
    private ZuulSwaggerProperties zuulSwaggerProperties;

    private static final String SWAGGER = "/v2/api-docs";

    /**
     * 过滤器类型，有pre、routing、post、error四种。
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序，数值越小优先级越高。
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否进行过滤，返回true会执行过滤。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 自定义的过滤器逻辑，当shouldFilter()返回true时会执行。
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String uri = request.getRequestURI();
        if (uri.contains(SWAGGER)) {
            return null;
        }
        // 判断是否在白名单中
        if (zuulSwaggerProperties.getWhiteList().contains(uri)) {
            return null;
        }
        // 校验source
        String header = request.getHeader(Constants.SOURCE);
        if (StrUtil.isBlank(header)) {
            header = request.getParameter(Constants.SOURCE);
        }
        if (StrUtil.isBlank(header)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody(JSON.toJSONString(ResultUtil.fail(AdminUserErrorEnum.SOURCE_BLANK)));
            requestContext.getResponse().setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
            return null;
        }
        // 只拦截后台请求
        if (!SourceEnum.ADMIN.getCode().equals(header)) {
            return null;
        }
        // 校验token
        String token = request.getHeader(Constants.TOKEN);
        if (StrUtil.isBlank(token)) {
            requestContext.setSendZuulResponse(false);
            if (AjaxUtil.isAjax(request)) {
                requestContext.setResponseBody(JSON.toJSONString(ResultUtil.fail(AdminUserErrorEnum.TOKEN_BLANK)));
                requestContext.getResponse().setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
                return null;
            } else {
                // 重定向到登录地址
                try {
                    requestContext.getResponse().sendRedirect(zuulSwaggerProperties.getLoginUrl());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        // 调用sso服务验证token
        BaseResult<UserDTO> checkResult = loginServiceFeign.checkToken(token);
        // 验证未通过
        if (!checkResult.getResult()) {
            requestContext.setSendZuulResponse(false);
            // token验证失败 未登录
            if (AjaxUtil.isAjax(request)) {
                requestContext.setResponseBody(JSON.toJSONString(checkResult));
                requestContext.getResponse().setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
                return null;
            } else {
                // 重定向到登录地址
                try {
                    requestContext.getResponse().sendRedirect(zuulSwaggerProperties.getLoginUrl());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        UserDTO userDTO = checkResult.getData();
        requestContext.addZuulRequestHeader(Constants.ADMIN_USER, URLUtil.encode(JSON.toJSONString(userDTO)));
        requestContext.addZuulRequestHeader(Constants.SOURCE, header);
        return null;
    }
}

