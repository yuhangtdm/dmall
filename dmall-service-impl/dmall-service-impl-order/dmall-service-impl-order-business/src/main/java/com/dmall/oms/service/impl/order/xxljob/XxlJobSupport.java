package com.dmall.oms.service.impl.order.xxljob;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.oms.api.enums.OmsErrorEnum;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @description: XxlJobSupport
 * @author: created by hang.yu on 2020/4/18 15:11
 */
@Slf4j
@Component
public class XxlJobSupport {

    @Autowired
    private RestTemplate restTemplate;

    @Value("{xxl.job,admin.url}")
    private String xxlJobAdminUrl;

    private static final String ADD_JOB = "/jobinfo/add";

    private static final String DELETE_JOB = "/jobinfo/delete?id={}";

    /**
     * 调用xxl job admin新增任务
     */
    public int addJob(String jobName, String param, String jobDesc, String cron) {
        ResponseEntity<ReturnT> jobResponseEntity = restTemplate.postForEntity(xxlJobAdminUrl + ADD_JOB,
            XxlJobUtil.buildXxlJobInfo(jobName, param, jobDesc, cron), ReturnT.class);
        ReturnT body = jobResponseEntity.getBody();
        if (HttpStatus.OK != jobResponseEntity.getStatusCode() || body == null) {
            throw new BusinessException(OmsErrorEnum.CREATE_JOB_ERROR);
        } else {
            if (HttpStatus.OK.value() != body.getCode()) {
                throw new BusinessException(OmsErrorEnum.CREATE_JOB_ERROR);
            }
        }
        return Integer.parseInt(body.getContent().toString());
    }

    /**
     * 调用xxl job admin删除任务
     */
    public void deleteJob(int jobId) {
        ResponseEntity<ReturnT> jobResponseEntity = restTemplate.getForEntity(
            StrUtil.format("{}{}?id={}", xxlJobAdminUrl, DELETE_JOB, jobId), ReturnT.class);
        log.info("delete job,{}", jobResponseEntity);
    }
}
