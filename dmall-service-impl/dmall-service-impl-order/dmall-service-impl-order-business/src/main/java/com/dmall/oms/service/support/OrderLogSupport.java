package com.dmall.oms.service.support;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.oms.api.enums.OrderOperateEnum;
import com.dmall.oms.generator.dataobject.OrderLogDO;
import com.dmall.oms.generator.mapper.OrderLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: OrderLogSupport
 * @author: created by hang.yu on 2020/4/5 21:33
 */
@Component
public class OrderLogSupport {

    @Autowired
    private OrderLogMapper orderLogMapper;

    /**
     * 根据订单号查询
     */
    public List<OrderLogDO> listByOrderId(Long orderId) {
        return orderLogMapper.selectList(Wrappers.<OrderLogDO>lambdaQuery()
                .eq(OrderLogDO::getOrderId, orderId));
    }

    public void insert(Long orderId, OrderOperateEnum operateEnum, boolean admin) {
        insert(orderId, operateEnum, admin, null, null);
    }

    public void insert(Long orderId, OrderOperateEnum operateEnum, boolean admin, Long subOrderId) {
        insert(orderId, operateEnum, admin, subOrderId, null);
    }

    public void insert(Long orderId, OrderOperateEnum operateEnum, boolean admin, String content) {
        insert(orderId, operateEnum, admin, null, content);
    }

    public void insert(Long orderId, OrderOperateEnum operateEnum, boolean admin, Long subOrderId, String content) {
        OrderLogDO orderLogDO = new OrderLogDO();
        orderLogDO.setOrderId(orderId);
        orderLogDO.setSubOrderId(subOrderId);
        orderLogDO.setOperate(operateEnum.getCode());
        if (admin) {
            AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
            if (adminUserDTO != null) {
                orderLogDO.setOperator(adminUserDTO.getRealName());
            }
        } else {
            PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
            if (portalMemberDTO != null) {
                orderLogDO.setOperator(portalMemberDTO.getMemberName());
            }
        }
        if (StrUtil.isBlank(content)) {
            orderLogDO.setLogContent(StrUtil.format("{} {}", orderLogDO.getOperator(), operateEnum.getDesc()));
        } else {
            orderLogDO.setLogContent(content);
        }
        orderLogMapper.insert(orderLogDO);
    }

}
