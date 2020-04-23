package com.dmall.common.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: Cookie工具类
 * @author: created by hang.yu on 2020/2/12 23:44
 */
public class CookieUtil {

    private CookieUtil() {
    }

    /**
     * 添加cookie
     */
    public static void addCookie(HttpServletResponse response, String name, String value,
                                 int maxAge, boolean isEncode) {
        if (isEncode) {
            // 解决中文问题
            value = URLUtil.encodeAll(value);
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(StrUtil.SLASH);
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie uid = new Cookie(name, null);
        uid.setPath(StrUtil.SLASH);
        uid.setMaxAge(0);
        response.addCookie(uid);
    }

    /**
     * 获取cookie值
     */
    public static String getCookie(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie != null){
                if (cookieName.equals(cookie.getName())) {
                    if (isDecoder) {//如果涉及中文
                        return URLUtil.decode(cookie.getValue());
                    } else {
                        return cookie.getValue();
                    }
                }
            }
        }
        return null;
    }


}