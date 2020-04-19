package com.dmall.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: Cron工具类
 * @author: created by hang.yu on 2020/4/18 21:48
 */
public class CronUtil {

    private CronUtil(){}

    private static final String dateFormat = "ss mm HH dd MM ? yyyy";

    /**
     * 通过输入指定日期时间生成cron表达式
     */
    public static String getCron(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }

        return formatTimeStr;
    }

    /**
     *根据当前日期增加天数后生成cron表达式
     */
    public static String getCronAddDay(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return getCron(calendar.getTime());
    }

}
