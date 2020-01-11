package com.dmall.framework.zuul;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: AjaxUtil
 * @author: created by hang.yu on 2020/1/6 22:49
 */
public class AjaxUtil {

    private static final String HEADER = "X-Requested-With";

    private static final String AJAX_REQUEST = "XMLHttpRequest";

    /**
     * 判断是否是  Ajax 请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        return AJAX_REQUEST.equals(request.getHeader(HEADER));
    }

}
