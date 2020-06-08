package com.dmall.oms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.oms.api.enums.JobTypeEnum;
import com.dmall.oms.generator.dataobject.SubOrderJobDO;
import com.dmall.oms.generator.mapper.SubOrderJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: SubOrderJobSupport
 * @author: created by hang.yu on 2020/4/18 21:32
 */
@Component
public class SubOrderJobSupport {

    @Autowired
    private SubOrderJobMapper subOrderJobMapper;

    public void insert(Long subOrderId, int xxlJobId, JobTypeEnum jobTypeEnum) {
        SubOrderJobDO subOrderJobDO = new SubOrderJobDO();
        subOrderJobDO.setSubOrderId(subOrderId);
        subOrderJobDO.setXxlJobId(xxlJobId);
        subOrderJobDO.setType(jobTypeEnum.getCode());
        subOrderJobMapper.insert(subOrderJobDO);
    }

    /**
     * 根据子订单号查询
     */
    public SubOrderJobDO findBySubOrderId(Long subOrderId) {
        return subOrderJobMapper.selectOne(Wrappers.<SubOrderJobDO>lambdaQuery()
            .eq(SubOrderJobDO::getSubOrderId, subOrderId));
    }
}
