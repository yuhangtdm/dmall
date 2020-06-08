package com.dmall.oms.service.impl.order.handler.aftersale;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.aftersaleapproval.AfterSaleApprovalRequestDTO;
import com.dmall.oms.api.enums.*;
import com.dmall.oms.feign.PaymentFeign;
import com.dmall.oms.feign.SkuFeign;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
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
 * @description: 售后审核处理器
 * @author: created by hang.yu on 2020/4/15 22:26
 */
@Component
public class AfterSaleApprovalHandler
    extends AbstractCommonHandler<AfterSaleApprovalRequestDTO, OrderAfterSaleApplyDO, Long> {

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
    private OrderMapper orderMapper;

    @Autowired
    private OrderAfterSaleLogSupport orderAfterSaleLogSupport;

    @Autowired
    private OmsValidate omsValidate;

    @Override
    public BaseResult<Long> processor(AfterSaleApprovalRequestDTO requestDTO) {
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = omsValidate.validateOrderAfterSale(requestDTO.getAfterSaleId());
        // 校验orderItem存在
        OrderItemDO orderItemDO = omsValidate.validateOrderItem(orderAfterSaleApplyDO.getOrderItemId());
        // 校验order存在
        OrderDO orderDO = omsValidate.validateOrder(orderItemDO.getOrderId());
        if (AfterSaleTypeEnum.REFUND.getCode().equals(orderAfterSaleApplyDO.getType())) {
            // 退款
            if (requestDTO.getResult()) {
                if (AfterSaleStatusEnum.REFUND_PROGRESS.getCode().equals(orderAfterSaleApplyDO.getStatus())) {
                    return ResultUtil.fail(OmsErrorEnum.AFTER_SALE_APPROVAL);
                }
                // 调用支付服务的退款接口
                BaseResult baseResult = paymentFeign.applyRefund(AfterSaleSupport.buildApplyRefundRequest(orderItemDO,
                    orderDO.getPaymentType(), orderAfterSaleApplyDO.getReason()));
                if (baseResult.getResult()) {
                    return ResultUtil.fail(baseResult.getCode(), baseResult.getMsg());
                }
                // 调用商品服务释放库存
                skuFeign.unLockStock(AfterSaleSupport.buildStockRequest(orderItemDO));
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
                // 同步订单状态到es
                syncEsOrderSupport.sendOrderEsMq(orderDO.getId());
                // 修改售后服务表
                orderAfterSaleApplyDO.setRefundTime(new Date());
                orderAfterSaleApplyDO.setStatus(AfterSaleStatusEnum.COMPLETED.getCode());
            } else {
                orderAfterSaleApplyDO.setStatus(AfterSaleStatusEnum.REFUSE.getCode());
            }
        } else {
            // 退货
            if (AfterSaleStatusEnum.APPLY.getCode().equals(orderAfterSaleApplyDO.getStatus())) {
                return ResultUtil.fail(OmsErrorEnum.AFTER_SALE_APPROVAL);
            }
            // 修改售后服务表
            if (requestDTO.getResult()) {
                orderAfterSaleApplyDO.setStatus(AfterSaleStatusEnum.WAIT_SEND_BACK.getCode());
                orderAfterSaleApplyDO.setSellerName(requestDTO.getSellerName());
                orderAfterSaleApplyDO.setSellerPhone(requestDTO.getSellerPhone());
                orderAfterSaleApplyDO.setSellerDetailAddress(requestDTO.getSellerAddressDetail());
            } else {
                orderAfterSaleApplyDO.setStatus(AfterSaleStatusEnum.REFUSE.getCode());
            }
        }
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        orderAfterSaleApplyDO.setHandleMan(adminUserDTO.getId());
        orderAfterSaleApplyDO.setApprovalTime(new Date());
        orderAfterSaleApplyDO.setHandleNote(requestDTO.getHandleNote());
        orderAfterSaleApplyMapper.updateById(orderAfterSaleApplyDO);

        // 新增售后日志记录
        orderAfterSaleLogSupport.insertAfterSaleLog(orderAfterSaleApplyDO.getId(), AfterSaleLogTypeEnum.SYSTEM,
            AfterSaleLogTitleEnum.APPROVAL, requestDTO.getHandleNote());
        return ResultUtil.success(requestDTO.getAfterSaleId());
    }

}
