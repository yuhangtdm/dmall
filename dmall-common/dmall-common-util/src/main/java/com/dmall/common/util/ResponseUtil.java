package com.dmall.common.util;

import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.dmall.common.constants.Constants;
import com.dmall.common.dto.BaseResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @description: 响应工具类输出json
 * @author: created by hang.yu on 2020/1/12 15:26
 */
public class ResponseUtil {

    public static void writeJson(HttpServletResponse response, BaseResult baseResult){
        try {
            response.setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
            response.getWriter().write(JSON.toJSONString(baseResult));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJson(HttpServletResponse response, String json){
        try {
            response.setContentType(ContentType.JSON.toString(Charset.forName(Constants.DEFAULT_CHARSET)));
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
