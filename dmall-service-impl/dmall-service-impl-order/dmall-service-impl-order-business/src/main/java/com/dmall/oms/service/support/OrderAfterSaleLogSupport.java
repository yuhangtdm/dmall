package com.dmall.oms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.oms.api.enums.AfterSaleLogTitleEnum;
import com.dmall.oms.api.enums.AfterSaleLogTypeEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleLogDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: OrderAfterSaleLogSupport
 * @author: created by hang.yu on 2020/4/16 22:05
 */
@Component
public class OrderAfterSaleLogSupport {

    @Autowired
    private OrderAfterSaleLogMapper orderAfterSaleLogMapper;

    /**
     * 插入售后日志记录
     */
    public void insertAfterSaleLog(Long afterSaleId, AfterSaleLogTypeEnum logType,
        AfterSaleLogTitleEnum logTitle, String logContent) {
        OrderAfterSaleLogDO orderAfterSaleLogDO = new OrderAfterSaleLogDO();
        orderAfterSaleLogDO.setAfterSaleApplyId(afterSaleId);
        orderAfterSaleLogDO.setLogType(logType.getCode());
        orderAfterSaleLogDO.setLogTitle(logTitle.getDesc());
        orderAfterSaleLogDO.setLogContent(logContent);
        orderAfterSaleLogMapper.insert(orderAfterSaleLogDO);
    }

    /**
     * 根据售后单号查询日志列表
     */
    public List<OrderAfterSaleLogDO> listByAfterSaleId(Long afterSaleId) {
        return orderAfterSaleLogMapper.selectList(Wrappers.<OrderAfterSaleLogDO>lambdaQuery()
            .eq(OrderAfterSaleLogDO::getAfterSaleApplyId, afterSaleId));
    }
}
