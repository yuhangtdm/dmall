package com.dmall.common.util;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;

/**
 * @description: 运费计算器工具类
 * @author: created by hang.yu on 2020/3/28 16:14
 */
public class FreightMoneyUtil {

    /**
     * 最小免运费金额
     */
    public static final BigDecimal MIN_FREE_FREIGHT_MONEY = BigDecimal.valueOf(50);

    /**
     * 运费
     */
    public static final BigDecimal FREIGHT_MONEY = BigDecimal.valueOf(10);

    /**
     * 获取运费
     */
    public static BigDecimal getFreightMoney(BigDecimal totalSkuMoney) {
        if (NumberUtil.isLess(totalSkuMoney, MIN_FREE_FREIGHT_MONEY)) {
            return FREIGHT_MONEY;
        }
        return BigDecimal.ZERO;
    }
}
