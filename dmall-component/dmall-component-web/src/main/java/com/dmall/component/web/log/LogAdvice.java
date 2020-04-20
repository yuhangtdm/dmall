package com.dmall.component.web.log;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.JsonUtil;
import com.dmall.common.util.RequestUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * @description: 日志切面
 * @author: created by hang.yu on 2019/11/10 18:46
 */
@Slf4j
public class LogAdvice implements MethodInterceptor {

    private final Environment environment;

    public LogAdvice(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        WebLog webLog = new WebLog();
        try {
            //获取当前请求对象
            HttpServletRequest request = RequestUtil.getRequest();
            //记录请求信息
            Method method = invocation.getMethod();
            webLog.setRequestIp(request.getRemoteAddr());
            AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
            if (adminUserDTO != null) {
                webLog.setUserId(adminUserDTO.getId());
                webLog.setUserName(adminUserDTO.getUserName());
            }
            PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
            if (portalMemberDTO != null) {
                webLog.setUserId(portalMemberDTO.getId());
                webLog.setUserName(portalMemberDTO.getMemberName());
            }
            webLog.setUrl(request.getRequestURL().toString());
            webLog.setUri(request.getRequestURI());
            webLog.setRequestMethod(request.getMethod());
            webLog.setStartTime(new Date());
            webLog.setClassName(method.getDeclaringClass().getName());
            webLog.setMethodName(method.getName());
            webLog.setEnv(environment.getActiveProfiles().length == 1 ? environment.getActiveProfiles()[0] : Constants.LOCAL);
            webLog.setAppName(environment.getProperty("spring.application.name"));
            webLog.setThreadName(Thread.currentThread().getName());
            webLog.setTitle(webLog.getClassName() + StrUtil.C_DOT + webLog.getMethodName());
            webLog.setParams(getParameter(method, invocation.getArguments()));
            Enumeration<String> headerNames = request.getHeaderNames();
            Map<String, String> header = Maps.newLinkedHashMap();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                header.put(key, request.getHeader(key));
            }
            webLog.setRequestHeader(header);
            Object result = invocation.proceed();
            webLog.setResult(result);
            return result;
        } catch (Throwable e) {
            webLog.setException(e.getMessage());
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            webLog.setSpendTime((end - start) + "ms");
            log.info("\n{}", JsonUtil.toJson(webLog));
        }
    }

    /**
     * 获取参数
     */
    private Object getParameter(Method method, Object[] args) {
        Parameter[] parameters = method.getParameters();
        if (parameters.length == 0) {
            return "";
        } else if (parameters.length == 1) {
            if (originalParam(parameters[0].getType())) {
                return "";
            } else {
                return args[0];
            }
        } else {
            Map<String, Object> params = Maps.newHashMap();
            for (int i = 0; i < parameters.length; i++) {
                if (originalParam(parameters[i].getType())) {
                    continue;
                }
                params.put(parameters[i].getName(), args[i]);
            }
            return params;
        }
    }

    private boolean originalParam(Class<?> clazz) {
        return clazz == HttpServletRequest.class || clazz == HttpServletResponse.class
                || clazz == HttpSession.class || clazz == MultipartFile.class;
    }

}
