package com.dmall.component.web.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 请求日志实体
 * @author: created by hang.yu on 2019/11/10 18:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebLog {

    /**
     * 请求ip
     */
    @JSONField(ordinal = 1)
    String requestIp;

    /**
     * 请求人id
     */
    @JSONField(ordinal = 2)
    Long userId;

    /**
     * 请求人昵称
     */
    @JSONField(ordinal = 3)
    String userName;

    /**
     * 请求地址
     */
    @JSONField(ordinal = 4)
    String url;

    /**
     * 请求uri
     */
    @JSONField(ordinal = 5)
    String uri;

    /**
     * 请求方法
     */
    @JSONField(ordinal = 6)
    String requestMethod;

    /**
     * 请求时间
     */
    @JSONField(ordinal = 7, format = "YYYY-MM-dd hh:mm:ss")
    Date startTime;

    /**
     * 方法耗时
     */

    @JSONField(ordinal = 8)
    String spendTime;

    /**
     * 请求类名
     */
    @JSONField(ordinal = 9)
    String className;

    /**
     * 请求方法名
     */
    @JSONField(ordinal = 10)
    String methodName;

    /**
     * 方法参数
     */
    @JSONField(ordinal = 11)
    Object params;

    /**
     * 返回结果
     */
    @JSONField(ordinal = 12)
    Object result;

    /**
     * 异常信息
     */
    @JSONField(ordinal = 13)
    String exception;

    /**
     * 请求头信息
     */
    @JSONField(ordinal = 14)
    JSONObject requestHeader;

    /**
     * 环境
     */
    @JSONField(ordinal = 15)
    String env;

    /**
     * 线程名称
     */
    @JSONField(ordinal = 16)
    String threadName;

    /**
     * 日志标题 默认 包名.类名.方法名
     */
    @JSONField(ordinal = 17)
    String title;

    /**
     * 日志标题 默认 包名.类名.方法名
     */
    @JSONField(ordinal = 17)
    String appName;

    /**
     * traceId
     */
    @JSONField(ordinal = 18)
    String traceId;

    /**
     * spanId
     */
    @JSONField(ordinal = 19)
    String spanId;
}
