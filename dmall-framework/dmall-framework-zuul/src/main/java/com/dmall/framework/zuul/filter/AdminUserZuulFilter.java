package com.dmall.framework.zuul.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.dmall.common.constants.Constants;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.AjaxUtil;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.framework.zuul.AdminUserErrorEnum;
import com.dmall.framework.zuul.feign.LoginServiceFeign;
import com.dmall.common.model.user.UserDTO;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @description: 后台用户过滤器
 * @author: created by hang.yu on 2020/1/6 22:35
 */
@Component
public class AdminUserZuulFilter extends ZuulFilter {

    /**
     * 免登录白名单
     */
    @Value("dmall.zuul.whiteList")
    private List<String> whiteList;

    @Value("dmall.zuul.loginUrl")
    private String loginUrl;

    @Autowired
    private LoginServiceFeign loginServiceFeign;

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
        return 2;
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
        if (whiteList.contains(uri)) {
            // 放行
            return null;
        }
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
                    requestContext.getResponse().sendRedirect(loginUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        // 调用sso服务验证token
        BaseResult<UserDTO> checkResult = loginServiceFeign.checkToken(token);
        if (!checkResult.getResult()) {
            // token验证失败 未登录
            if (AjaxUtil.isAjax(request)) {
                requestContext.setResponseBody(JSON.toJSONString(checkResult));
                requestContext.getResponse().setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
                return null;
            } else {
                // 重定向到登录地址
                try {
                    requestContext.getResponse().sendRedirect(loginUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        UserDTO userDTO = checkResult.getData();
        requestContext.addZuulRequestHeader(Constants.ADMIN_USER, JSON.toJSONString(userDTO));
        return null;
    }
}

