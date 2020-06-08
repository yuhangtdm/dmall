package com.dmall.framework.zuul.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.ContentType;
import com.dmall.common.constants.Constants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.enums.SourceEnum;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.JsonUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.framework.zuul.AdminUserErrorEnum;
import com.dmall.framework.zuul.configuration.WhiteListProperties;
import com.dmall.framework.zuul.feign.AdminLoginServiceFeign;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

/**
 * @description: 后台用户过滤器
 * @author: created by hang.yu on 2020/1/6 22:35
 */
@Component
public class AdminUserZuulFilter extends ZuulFilter {

    @Autowired
    private AdminLoginServiceFeign adminLoginServiceFeign;

    @Autowired
    private WhiteListProperties whiteListProperties;

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
        if (uri.contains(Constants.SWAGGER)) {
            return null;
        }
        // 判断是否在白名单中
        if (whiteListProperties.getAdmin().contains(uri)) {
            return null;
        }

        // 校验source
        String header = request.getHeader(Constants.SOURCE);
        if (StrUtil.isBlank(header)) {
            header = request.getParameter(Constants.SOURCE);
        }
        if (StrUtil.isBlank(header)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody(JsonUtil.toJson(ResultUtil.fail(AdminUserErrorEnum.SOURCE_NOT_BLANK)));
            requestContext.getResponse()
                .setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
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
            requestContext.setResponseBody(JsonUtil.toJson(ResultUtil.fail(BasicStatusEnum.USER_NOT_LOGIN)));
            requestContext.getResponse()
                .setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
            return null;
        }
        // 调用sso服务验证token
        BaseResult<AdminUserDTO> checkResult = adminLoginServiceFeign.checkToken(token);
        // 验证未通过
        if (!checkResult.getResult()) {
            requestContext.setSendZuulResponse(false);
            // token验证失败 未登录
            requestContext.setResponseBody(JsonUtil.toJson(checkResult));
            requestContext.getResponse()
                .setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
            return null;
        }
        AdminUserDTO adminUserDTO = checkResult.getData();
        requestContext.addZuulRequestHeader(Constants.ADMIN_USER, URLUtil.encode(JsonUtil.toJson(adminUserDTO)));
        requestContext.addZuulRequestHeader(Constants.SOURCE, header);
        return null;
    }
}
