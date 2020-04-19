package com.dmall.common.util;

import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.dmall.common.constants.Constants;
import com.dmall.common.dto.BaseResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @description: 响应工具类输出json
 * @author: created by hang.yu on 2020/1/12 15:26
 */
public class ResponseUtil {

    private ResponseUtil() {

    }

    /**
     * 输出json字符串
     */
    public static void writeJson(HttpServletResponse response, BaseResult baseResult) {
        try {
            response.setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
            response.getWriter().write(JSONUtil.toJSONString(baseResult));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出json字符串
     */
    public static void writeJson(HttpServletResponse response, String json) {
        try {
            response.setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取HttpServletResponse对象
     */
    public static HttpServletResponse getResponse() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return null;
        }
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
