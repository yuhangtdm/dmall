package com.dmall.oms.service.impl.order.xxljob;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.model.xxljob.XxlJobInfo;
import com.dmall.oms.service.impl.order.OrderConstants;

/**
 * @description: XxlJobUtil
 * @author: created by hang.yu on 2020/4/18 15:09
 */
public class XxlJobUtil {

    /**
     * 构建XxlJobInfo
     *
     * @param jobName 任务名称
     * @param param   任务参数
     * @param jobDesc 描述
     * @param cron    表达式
     */
    public static XxlJobInfo buildXxlJobInfo(String jobName, String param, String jobDesc, String cron) {
        XxlJobInfo xxlJobInfo = new XxlJobInfo();
        xxlJobInfo.setJobGroup(OrderConstants.DEFAULT_JOB_GROUP);
        xxlJobInfo.setJobDesc(jobDesc);
        xxlJobInfo.setJobCron(cron);
        xxlJobInfo.setExecutorParam(param);
        xxlJobInfo.setExecutorHandler(jobName);
        return xxlJobInfo;
    }
}
