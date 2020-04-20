package com.dmall.component.web.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * @description: 请求日志实体
 * @author: created by hang.yu on 2019/11/10 18:41
 */
@Data
public class WebLog {

    /**
     * 请求ip
     */
    String requestIp;

    /**
     * 请求人id
     */
    Long userId;

    /**
     * 请求人昵称
     */
    String userName;

    /**
     * 请求地址
     */
    String url;

    /**
     * 请求uri
     */
    String uri;

    /**
     * 请求方式
     */
    String requestMethod;

    /**
     * 请求时间
     */
    Date startTime;

    /**
     * 方法耗时
     */
    String spendTime;

    /**
     * 请求类名
     */
    String className;

    /**
     * 请求方法名
     */
    String methodName;

    /**
     * 方法参数
     */
    Object params;

    /**
     * 返回结果
     */
    Object result;

    /**
     * 异常信息
     */
    String exception;

    /**
     * 请求头信息
     */
    Map<String, String> requestHeader;

    /**
     * 环境
     */
    String env;

    /**
     * 线程名称
     */
    String threadName;

    /**
     * 日志标题 默认 包名.类名.方法名
     */
    String title;

    /**
     * 日志标题 默认 包名.类名.方法名
     */
    String appName;

    /**
     * traceId
     */
    String traceId;

    /**
     * spanId
     */
    String spanId;
}
