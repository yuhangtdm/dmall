package com.dmall.oms.service.impl.order.handler;

import java.util.Date;

import cn.hutool.core.collection.CollUtil;
import com.dmall.oms.api.enums.*;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import com.dmall.oms.service.impl.support.SubOrderSupport;
import com.dmall.oms.service.impl.support.SyncEsOrderSupport;
import com.dmall.pms.api.dto.sku.request.SkuStockRequestDTO;
import com.google.common.collect.Lists;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.applyrefund.OrderApplyRefundRequestDTO;
import com.dmall.oms.feign.PaymentFeign;
import com.dmall.oms.feign.SkuFeign;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.pay.api.dto.applyrefund.ApplyRefundRequestDTO;
import com.dmall.pms.api.dto.sku.request.StockRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 申请退款处理器
 * @author: created by hang.yu on 2020/4/13 22:11
 */
@Component
public class ApplyRefundHandler extends AbstractCommonHandler<OrderApplyRefundRequestDTO, OrderAfterSaleApplyDO, Long> {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PaymentFeign paymentFeign;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SubOrderSupport subOrderSupport;

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private SyncEsOrderSupport syncEsOrderSupport;

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    @Override
    public BaseResult<Long> processor(OrderApplyRefundRequestDTO requestDTO) {
        // 校验orderItem存在
        OrderItemDO orderItemDO = orderItemMapper.selectById(requestDTO.getOrderItemId());
        if (orderItemDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        // 校验order存在
        OrderDO orderDO = orderMapper.selectById(orderItemDO.getOrderId());
        if (orderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        // 校验订单状态必须为 待发货
        if (!OrderStatusEnum.WAIT_SHIP.getCode().equals(orderDO.getStatus())) {
            return ResultUtil.fail(OrderErrorEnum.APPLY_REFUND_ERROR);
        }
        // 根据订单支付类型调用具体的退款结果
        BaseResult baseResult = paymentFeign.applyRefund(buildApplyRefundRequest(orderItemDO, orderDO.getPaymentType(), requestDTO));
        if (baseResult.getResult()) {
            return ResultUtil.fail(baseResult.getCode(), baseResult.getMsg());
        }
        // 释放库存
        skuFeign.unLockStock(buildStockRequest(orderItemDO));
        // 修改订单状态为交易关闭 不可分单、发货、收货、评价
        orderDO.setStatus(OrderStatusEnum.CLOSED.getCode());
        orderMapper.updateById(orderDO);
        // 如果已分单 则修改子订单状态
        List<SubOrderDO> subOrderList = subOrderSupport.listByOrderId(orderDO.getId());
        if (CollUtil.isNotEmpty(subOrderList)) {
            for (SubOrderDO subOrderDO : subOrderList) {
                subOrderDO.setStatus(SubOrderStatusEnum.CLOSED.getCode());
                subOrderMapper.updateById(subOrderDO);
            }
        }
        // 同步到es
        syncEsOrderSupport.sendOrderEsMq(orderDO.getId());
        // 新增售后服务表
        Long id = insertAfterSale(requestDTO, orderItemDO, orderDO);
        return ResultUtil.success(id);
    }


    /**
     * 构建申请退款实体
     */
    private ApplyRefundRequestDTO buildApplyRefundRequest(OrderItemDO orderItemDO, Integer paymentType,
                                                          OrderApplyRefundRequestDTO requestDTO) {
        ApplyRefundRequestDTO applyRefundRequestDTO = new ApplyRefundRequestDTO();
        applyRefundRequestDTO.setOrderId(orderItemDO.getOrderId());
        applyRefundRequestDTO.setOrderItemId(orderItemDO.getId());
        applyRefundRequestDTO.setSubOrderId(orderItemDO.getSubOrderId());
        applyRefundRequestDTO.setAmount(orderItemDO.getRealPrice());
        applyRefundRequestDTO.setPaymentType(paymentType);
        applyRefundRequestDTO.setRefundReason(requestDTO.getReason());
        return applyRefundRequestDTO;
    }

    /**
     * 构建释放库存请求实体
     */
    private StockRequestDTO buildStockRequest(OrderItemDO orderItemDO) {
        StockRequestDTO stockRequestDTO = new StockRequestDTO();
        List<SkuStockRequestDTO> skuList = Lists.newArrayList(new SkuStockRequestDTO()
                .setSkuId(orderItemDO.getSkuId()).setNumber(orderItemDO.getSkuNumber()));
        stockRequestDTO.setSku(skuList);
        return stockRequestDTO;
    }

    /**
     * 新增售后表
     */
    private Long insertAfterSale(OrderApplyRefundRequestDTO requestDTO, OrderItemDO orderItemDO, OrderDO orderDO) {
        OrderAfterSaleApplyDO entity = new OrderAfterSaleApplyDO();
        entity.setOrderId(orderDO.getId());
        entity.setSubOrderId(orderItemDO.getSubOrderId());
        entity.setOrderItemId(orderItemDO.getId());
        entity.setSkuId(orderItemDO.getSkuId());
        entity.setType(AfterSaleTypeEnum.REFUND.getCode());
        entity.setStatus(AfterSaleStatusEnum.APPLY.getCode());
        entity.setReason(requestDTO.getReason());
        entity.setDescription(requestDTO.getDescription());
        entity.setRefundType(1);
        entity.setApplyTime(new Date());
        orderAfterSaleApplyMapper.insert(entity);
        return entity.getId();
    }

}
