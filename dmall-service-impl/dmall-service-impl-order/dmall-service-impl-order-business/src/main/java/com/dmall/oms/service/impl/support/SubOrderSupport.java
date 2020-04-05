package com.dmall.oms.service.impl.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: SubOrderSupport
 * @author: created by hang.yu on 2020/4/5 17:17
 */
@Component
public class SubOrderSupport {
    @Autowired
    private SubOrderMapper subOrderMapper;

    /**
     * 根据订单号查询子订单列表
     */
    public List<SubOrderDO> listByOrderId(Long orderId) {
        return subOrderMapper.selectList(Wrappers.<SubOrderDO>lambdaQuery()
                .eq(SubOrderDO::getOrderId, orderId));
    }
}
