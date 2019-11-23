package com.dmall.component.web.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmall.component.web.entity.WebLog;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.Enumeration;

/**
 * @description: 日志切面
 * @author: created by hang.yu on 2019/11/10 18:46
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class LogAdvice implements MethodInterceptor {

    private Environment environment;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        WebLog webLog = new WebLog();
        try {
            //获取当前请求对象
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            //记录请求信息
            Method method = invocation.getMethod();
            webLog.setRequestIp(request.getRemoteAddr());
            // 用户id和昵称暂时设为空
            webLog.setUserId(0L);
            webLog.setUserName("");
            webLog.setUrl(request.getRequestURL().toString());
            webLog.setUri(request.getRequestURI());
            webLog.setRequestMethod(request.getMethod());
            webLog.setStartTime(new Date());
            webLog.setClassName(method.getDeclaringClass().getName());
            webLog.setMethodName(method.getName());
            webLog.setEnv("dev");
            webLog.setAppName(environment.getProperty("spring.application.name"));
            webLog.setThreadName(Thread.currentThread().getName());
            webLog.setTitle(webLog.getClassName() + "." + webLog.getMethodName());
            webLog.setParams(getParameter(method, invocation.getArguments()));
            Enumeration<String> headerNames = request.getHeaderNames();
            JSONObject header = new JSONObject();
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
            log.info("\n{}", JSON.toJSONString(webLog, true));
        }
    }

    /**
     * 获取参数
     */
    private Object getParameter(Method method, Object[] args) {
        Parameter[] parameters = method.getParameters();
        if (parameters.length == 0){
            return "";
        }else if (parameters.length == 1){
            if (originalParam(parameters[0].getType())){
                return "";
            }else {
                return args[0];
            }
        }else {
            JSONObject params = new JSONObject();
            for (int i = 0; i < parameters.length; i++) {
                if (originalParam(parameters[i].getType())){
                    continue;
                }
                params.put(parameters[i].getName(), args[i]);
            }
            return params;
        }
    }

    private boolean originalParam(Class<?> clazz) {
        return clazz == HttpServletRequest.class || clazz == HttpServletResponse.class
            || clazz == HttpSession.class;
    }

}
