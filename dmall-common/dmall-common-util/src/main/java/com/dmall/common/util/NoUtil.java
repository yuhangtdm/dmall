package com.dmall.common.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * @description: 编号生成工具类
 * @author: created by hang.yu on 2019/12/14 10:00
 */
public class NoUtil {

    private static final String PRODUCT_NO_PREFIX = "P" ;

    private static final String SKU_NO_PREFIX = "K" ;

    /**
     * 生成商品编号 P+时间戳+四位随机数
     */
    public static String generateProductNo() {
        return PRODUCT_NO_PREFIX + getTime() + RandomUtil.randomNumbers(4);
    }

    /**
     * 生成sku编号 S+时间戳+四位随机数
     */
    public static String generateSkuNo() {
        return SKU_NO_PREFIX + getTime() + RandomUtil.randomNumbers(4);
    }

    private static String getTime() {
        return DateUtil.format(DateUtil.date(System.currentTimeMillis()), DatePattern.PURE_DATETIME_MS_PATTERN);
    }

}
