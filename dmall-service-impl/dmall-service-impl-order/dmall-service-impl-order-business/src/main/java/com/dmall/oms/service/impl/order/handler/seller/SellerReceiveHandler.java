package com.dmall.oms.service.impl.order.handler.seller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.*;
import com.dmall.oms.feign.PaymentFeign;
import com.dmall.oms.feign.SkuFeign;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import com.dmall.oms.service.support.AfterSaleSupport;
import com.dmall.oms.service.support.OrderAfterSaleLogSupport;
import com.dmall.oms.service.support.SubOrderSupport;
import com.dmall.oms.service.support.SyncEsOrderSupport;
import com.dmall.oms.service.validate.OmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @description: 卖家确认收货处理器
 * @author: created by hang.yu on 2020/4/15 23:08
 */
@Component
public class SellerReceiveHandler extends AbstractCommonHandler<Long, OrderAfterSaleApplyDO, Long> {

    private static final String RECEIVE_LOG_CONTENT = "您的服务单{}的商品已收到";

    private static final String REFUND_LOG_CONTENT = "您的服务单{}财务已退款,请您注意查收";

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

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
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderAfterSaleLogSupport orderAfterSaleLogSupport;

    @Autowired
    private OmsValidate omsValidate;

    @Override
    public BaseResult processor(Long afterSaleId) {
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = omsValidate.validateOrderAfterSale(afterSaleId);
        if (AfterSaleStatusEnum.RE_PROGRESS.getCode().equals(orderAfterSaleApplyDO.getStatus())) {
            return ResultUtil.fail(OmsErrorEnum.AFTER_SALE_APPROVAL);
        }
        // 校验orderItem存在
        OrderItemDO orderItemDO = omsValidate.validateOrderItem(orderAfterSaleApplyDO.getOrderItemId());
        // 校验order存在
        OrderDO orderDO = omsValidate.validateOrder(orderItemDO.getOrderId());
        // 新增售后日志记录
        orderAfterSaleLogSupport.insertAfterSaleLog(orderAfterSaleApplyDO.getId(), AfterSaleLogTypeEnum.SYSTEM,
                AfterSaleLogTitleEnum.PRODUCT_RECEIVED, StrUtil.format(RECEIVE_LOG_CONTENT, orderAfterSaleApplyDO.getId()));
        // 调用支付服务的退款接口
        BaseResult baseResult = paymentFeign.applyRefund(AfterSaleSupport.buildApplyRefundRequest(orderItemDO,
                orderDO.getPaymentType(), orderAfterSaleApplyDO.getReason()));
        if (baseResult.getResult()) {
            return ResultUtil.fail(baseResult.getCode(), baseResult.getMsg());
        }
        // 调用商品服务释放库存
        skuFeign.unLockStock(AfterSaleSupport.buildStockRequest(orderItemDO));
        // 修改订单状态为已完成 不可分单、发货、收货、评价
        orderDO.setStatus(OrderStatusEnum.COMPLETED.getCode());
        orderMapper.updateById(orderDO);
        // 如果已分单 则修改子订单状态
        List<SubOrderDO> subOrderList = subOrderSupport.listByOrderId(orderDO.getId());
        if (CollUtil.isNotEmpty(subOrderList)) {
            for (SubOrderDO subOrderDO : subOrderList) {
                subOrderDO.setStatus(SubOrderStatusEnum.COMPLETED.getCode());
                subOrderMapper.updateById(subOrderDO);
            }
        }
        // 同步订单状态到es
        syncEsOrderSupport.sendOrderEsMq(orderDO.getId());
        // 修改售后服务表
        orderAfterSaleApplyDO.setRefundTime(new Date());
        orderAfterSaleApplyDO.setStatus(AfterSaleStatusEnum.COMPLETED.getCode());
        orderAfterSaleApplyMapper.updateById(orderAfterSaleApplyDO);
        // 新增售后日志记录
        orderAfterSaleLogSupport.insertAfterSaleLog(orderAfterSaleApplyDO.getId(), AfterSaleLogTypeEnum.SYSTEM,
                AfterSaleLogTitleEnum.REFUND_SUCCESS, StrUtil.format(REFUND_LOG_CONTENT, orderAfterSaleApplyDO.getId()));
        return ResultUtil.success(afterSaleId);
    }
}
