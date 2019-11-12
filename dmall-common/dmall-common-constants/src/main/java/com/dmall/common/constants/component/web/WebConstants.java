package com.dmall.common.constants.component.web;

/**
 * @description: web组件常量
 * @author: created by yuhang on 2019/11/10 15:40
 */
public interface WebConstants {

    /**
     * ERROR STATUS CODE
     */
    String ERROR_STATUS_CODE = "javax.servlet.error.status_code";

    /**
     * data参数
     */
    String DATA = "data";

    /**
     * 跳转地址
     */
    String FORWARD_ERROR = "forward:/error";

    /**
     * RESULT
     */
    String RESULT = "result";

    /**
     * CODE
     */
    String CODE = "code";

    /**
     * MSG
     */
    String MSG = "msg";

    /**
     * /actuator/hystrix.stream
     */
    String ACTUATOR_HYSTRIX_STREAM = "/actuator/hystrix.stream";

    /**
     * HystrixMetricsStreamServlet
     */
    String HYSTRIX_METRICS_STREAM_SERVLET = "HystrixMetricsStreamServlet";

}
