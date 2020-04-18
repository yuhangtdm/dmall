package com.dmall.oms.service.impl.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.EnumUtil;
import com.dmall.oms.api.dto.aftersaledetail.AfterSaleDTO;
import com.dmall.oms.api.dto.aftersaledetail.AfterSaleLogDTO;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.api.enums.AfterSaleTypeEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.dataobject.OrderAfterSaleLogDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.pay.api.dto.applyrefund.ApplyRefundRequestDTO;
import com.dmall.pms.api.dto.sku.request.SkuStockRequestDTO;
import com.dmall.pms.api.dto.sku.request.StockRequestDTO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: AfterSaleSupport
 * @author: created by hang.yu on 2020/4/15 23:12
 */
@Component
public class AfterSaleSupport {

    private static final List<Integer> STATUS = Lists.newArrayList(AfterSaleStatusEnum.APPLY.getCode(),
            AfterSaleStatusEnum.WAIT_SEND_BACK.getCode(), AfterSaleStatusEnum.RE_PROGRESS.getCode(),
            AfterSaleStatusEnum.REFUND_PROGRESS.getCode());

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderAfterSaleLogSupport orderAfterSaleLogSupport;

    /**
     * 根据订单号查询售后列表
     */
    public List<OrderAfterSaleApplyDO> listByOrderId(Long orderId) {
        return orderAfterSaleApplyMapper.selectList(Wrappers.<OrderAfterSaleApplyDO>lambdaQuery()
                .eq(OrderAfterSaleApplyDO::getOrderId, orderId));
    }

    /**
     * 根据订单项号查询售后列表
     */
    public List<OrderAfterSaleApplyDO> listByOrderItemId(Long orderItemId) {
        return orderAfterSaleApplyMapper.selectList(Wrappers.<OrderAfterSaleApplyDO>lambdaQuery()
                .eq(OrderAfterSaleApplyDO::getOrderItemId, orderItemId));
    }

    /**
     * 根据订单号查询不可删除订单状态的售后列表
     */
    public List<OrderAfterSaleApplyDO> listInNotDeleteStatus(Long orderId) {
        return orderAfterSaleApplyMapper.selectList(Wrappers.<OrderAfterSaleApplyDO>lambdaQuery()
                .eq(OrderAfterSaleApplyDO::getOrderId, orderId)
                .in(OrderAfterSaleApplyDO::getStatus, STATUS));
    }

    /**
     * 构建申请退款实体
     */
    public static ApplyRefundRequestDTO buildApplyRefundRequest(OrderItemDO orderItemDO, Integer paymentType, String reason) {
        ApplyRefundRequestDTO applyRefundRequestDTO = new ApplyRefundRequestDTO();
        applyRefundRequestDTO.setOrderId(orderItemDO.getOrderId());
        applyRefundRequestDTO.setOrderItemId(orderItemDO.getId());
        applyRefundRequestDTO.setSubOrderId(orderItemDO.getSubOrderId());
        applyRefundRequestDTO.setAmount(orderItemDO.getRealPrice());
        applyRefundRequestDTO.setPaymentType(paymentType);
        applyRefundRequestDTO.setRefundReason(reason);
        return applyRefundRequestDTO;
    }

    /**
     * 构建释放库存请求实体
     */
    public static StockRequestDTO buildStockRequest(OrderItemDO orderItemDO) {
        StockRequestDTO stockRequestDTO = new StockRequestDTO();
        List<SkuStockRequestDTO> skuList = Lists.newArrayList(new SkuStockRequestDTO()
                .setSkuId(orderItemDO.getSkuId()).setNumber(orderItemDO.getSkuNumber()));
        stockRequestDTO.setSku(skuList);
        return stockRequestDTO;
    }

    /**
     * 根据订单号构建售后列表
     */
    public List<AfterSaleDTO> buildAfterSaleList(Long orderId) {
        List<OrderAfterSaleApplyDO> orderAfterSaleApplyList = listByOrderId(orderId);
        return orderAfterSaleApplyList.stream().map(orderAfterSaleApplyDO -> buildAfterSale(orderAfterSaleApplyDO.getId()))
                .collect(Collectors.toList());
    }

    /**
     * 根据订单项号构建售后列表
     */
    public List<AfterSaleDTO> buildAfterSaleListByOrderItemId(Long orderItemId) {
        List<OrderAfterSaleApplyDO> orderAfterSaleApplyList = listByOrderItemId(orderItemId);
        return orderAfterSaleApplyList.stream().map(orderAfterSaleApplyDO -> buildAfterSale(orderAfterSaleApplyDO.getId()))
                .collect(Collectors.toList());
    }

    /**
     * 构建售后实体
     */
    public AfterSaleDTO buildAfterSale(Long afterSaleId) {
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = orderAfterSaleApplyMapper.selectById(afterSaleId);
        if (orderAfterSaleApplyDO == null) {
            return null;
        }
        OrderItemDO orderItemDO = orderItemMapper.selectById(orderAfterSaleApplyDO.getOrderItemId());
        if (orderItemDO == null) {
            return null;
        }
        AfterSaleDTO afterSaleDTO = BeanUtil.copyProperties(orderAfterSaleApplyDO, AfterSaleDTO.class);
        afterSaleDTO.setAfterSaleId(orderAfterSaleApplyDO.getId());
        afterSaleDTO.setType(EnumUtil.getCodeDescEnum(AfterSaleTypeEnum.class, orderAfterSaleApplyDO.getType()));
        afterSaleDTO.setStatus(EnumUtil.getCodeDescEnum(AfterSaleStatusEnum.class, orderAfterSaleApplyDO.getStatus()));

        List<OrderAfterSaleLogDO> orderAfterSaleLogList = orderAfterSaleLogSupport.listByAfterSaleId(orderAfterSaleApplyDO.getId());
        List<AfterSaleLogDTO> saleLogList = orderAfterSaleLogList.stream().map(orderAfterSaleLogDO -> {
            AfterSaleLogDTO afterSaleLog = new AfterSaleLogDTO();
            afterSaleLog.setLogId(orderAfterSaleLogDO.getId());
            afterSaleLog.setLogType(orderAfterSaleLogDO.getLogType());
            afterSaleLog.setLogTitle(orderAfterSaleLogDO.getLogTitle());
            afterSaleLog.setLogContent(orderAfterSaleLogDO.getLogContent());
            afterSaleLog.setCreateTime(orderAfterSaleLogDO.getGmtCreated());
            return afterSaleLog;
        }).collect(Collectors.toList());

        afterSaleDTO.setAfterSaleLogs(saleLogList);
        return afterSaleDTO;
    }
}
