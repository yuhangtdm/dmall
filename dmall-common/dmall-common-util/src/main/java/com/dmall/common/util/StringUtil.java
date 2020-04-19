package com.dmall.common.util;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @description: StringUtil
 * @author: created by hang.yu on 2020/4/4 12:45
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * ISO-8859-1
     */
    private static final String ISO = "ISO-8859-1";

    /**
     * utf-8
     */
    private static final String UTF = "utf-8";

    /**
     * ISO转utf-8解决乱码问题
     */
    public static String getIsoToUtf8(String str) {
        String newStr = StrUtil.EMPTY;
        ;
        if (StringUtils.isBlank(str)) {
            return newStr;
        }
        try {
            newStr = new String(str.getBytes(ISO), UTF);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newStr;
    }
}
