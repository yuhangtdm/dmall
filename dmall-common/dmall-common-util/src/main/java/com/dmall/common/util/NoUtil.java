package com.dmall.common.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * @description: 编号生成工具类
 * @author: created by hang.yu on 2019/12/14 10:00
 */
@Deprecated
public class NoUtil {


    /**
     * 生成商品编号时间戳+四位随机数
     */
    public static String generateProductId() {
        return getTime() + RandomUtil.randomNumbers(4);
    }

    /**
     * 生成sku编号 时间戳+四位随机数
     */
    public static Long generateSkuId() {
        return Long.valueOf(getTime() + RandomUtil.randomNumbers(4));
    }

    private static String getTime() {
        return DateUtil.format(DateUtil.date(System.currentTimeMillis()), DatePattern.PURE_DATETIME_MS_PATTERN);
    }

}
