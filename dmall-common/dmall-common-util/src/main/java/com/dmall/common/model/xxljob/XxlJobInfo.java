package com.dmall.common.model.xxljob;

import lombok.Data;

/**
 * @description: XxlJobInfo
 * @author: created by hang.yu on 2020/04/18 14:21
 */
@Data
public class XxlJobInfo {

    /**
     * 执行器主键ID
     */
    private int jobGroup;

    /**
     * 任务描述
     */
    private String jobDesc;

    /**
     * 执行器路由策略，默认使用故障转移
     */
    private String executorRouteStrategy = "FAILOVER";

    /**
     * 任务执行CRON表达式
     */
    private String jobCron;

    /**
     * 执行器，任务参数
     */
    private String executorParam;

    /**
     * 运行模式 默认使用 BEAN
     */
    private String glueType = "BEAN";

    /**
     * 执行器，任务Handler名称
     */
    private String executorHandler;

    /**
     * 阻塞处理策略 默认使用单机串行
     */
    private String executorBlockStrategy = "SERIAL_EXECUTION";

    /**
     * 负责人
     */
    private String author = "admin";

    /**
     * 报警邮件 默认邮箱
     */
    private String alarmEmail = "649411629@qq.com";

}
