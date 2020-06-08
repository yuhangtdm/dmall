package com.dmall.oms.service.impl.order.xxljob;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.oms.api.enums.DeliverTypeEnum;
import com.dmall.oms.api.enums.OrderCommentStatusEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.api.enums.SubOrderStatusEnum;
import com.dmall.oms.feign.CommentFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.dataobject.SubOrderJobDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import com.dmall.oms.service.impl.order.OrderConstants;
import com.dmall.oms.service.support.OrderItemSupport;
import com.dmall.oms.service.support.SubOrderJobSupport;
import com.dmall.oms.service.support.SubOrderSupport;
import com.dmall.oms.service.support.SyncEsOrderSupport;
import com.dmall.pms.api.dto.comment.request.SaveCommentRequestDTO;
import com.dmall.pms.api.enums.CommentLevelEnum;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: XxlJobHandler
 * @author: created by hang.yu on 2020/4/18 14:42
 */
@Component
public class XxlJobHandler {

    private static final String DEFAULT_GOOD_COMMENT = "默认好评";

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SubOrderSupport subOrderSupport;

    @Autowired
    private SyncEsOrderSupport syncEsOrderSupport;

    @Autowired
    private CommentFeign commentFeign;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Autowired
    private SubOrderJobSupport subOrderJobSupport;

    @Autowired
    private XxlJobSupport xxlJobSupport;

    /**
     * 自动确认收货
     */
    @XxlJob(OrderConstants.AUTO_RECEIVE_HANDLER)
    @Transactional(rollbackFor = Exception.class)
    public ReturnT<String> autoReceive(String param) {
        Long subOrderId = Long.valueOf(param);
        // 子订单存在 直接返回
        SubOrderDO subOrderDO = subOrderMapper.selectById(subOrderId);
        if (subOrderDO == null) {
            return ReturnT.SUCCESS;
        }
        // 订单存在 直接返回
        OrderDO orderDO = orderMapper.selectById(subOrderDO.getOrderId());
        if (orderDO == null) {
            return ReturnT.SUCCESS;
        }
        // 非待收货状态 直接返回
        if (!SubOrderStatusEnum.WAIT_SHIP.getCode().equals(subOrderDO.getStatus())) {
            return ReturnT.SUCCESS;
        }

        // 修改状态为已收货
        subOrderDO.setStatus(SubOrderStatusEnum.COMPLETED.getCode());
        subOrderDO.setDeliverType(DeliverTypeEnum.AUTO.getCode());
        subOrderMapper.updateById(subOrderDO);

        // 判断订单是否需要修改
        List<SubOrderDO> subOrderList = subOrderSupport.listByOrderId(orderDO.getId());
        List<SubOrderDO> completedList = subOrderList.stream()
            .filter(subOrder -> SubOrderStatusEnum.COMPLETED.getCode().equals(subOrder.getStatus()))
            .collect(Collectors.toList());

        if (subOrderList.size() == completedList.size()) {
            orderDO.setStatus(OrderStatusEnum.COMPLETED.getCode());
            orderMapper.updateById(orderDO);
        }
        // 同步到es
        syncEsOrderSupport.sendOrderEsMq(orderDO.getId());
        SubOrderJobDO bySubOrderId = subOrderJobSupport.findBySubOrderId(subOrderId);
        if (bySubOrderId != null) {
            xxlJobSupport.deleteJob(bySubOrderId.getXxlJobId());
        }
        return ReturnT.SUCCESS;

    }

    /**
     * 自动好评
     */
    @XxlJob(OrderConstants.AUTO_GOOD_COMMENT_HANDLER)
    @Transactional(rollbackFor = Exception.class)
    public ReturnT<String> autoGoodComment(String param) {
        String[] split = param.split(StrUtil.COLON);
        Long subOrderId = Long.valueOf(split[0]);

        SubOrderDO subOrderDO = subOrderMapper.selectById(subOrderId);
        if (subOrderDO == null) {
            return ReturnT.SUCCESS;
        }
        OrderDO orderDO = orderMapper.selectById(subOrderDO.getOrderId());
        if (orderDO == null) {
            return ReturnT.SUCCESS;
        }
        // 已全部评价则不可评价
        if (OrderCommentStatusEnum.ALL.getCode().equals(orderDO.getCommentStatus())) {
            return ReturnT.SUCCESS;
        }
        if (OrderCommentStatusEnum.ALL.getCode().equals(subOrderDO.getCommentStatus())) {
            return ReturnT.SUCCESS;
        }

        // 子订单 评价状态默认是 部分评价
        subOrderDO.setCommentStatus(OrderCommentStatusEnum.PART.getCode());
        orderDO.setCommentStatus(OrderCommentStatusEnum.PART.getCode());
        List<Long> skuIds =
            Arrays.stream(split[1].split(StrUtil.COMMA)).map(Long::valueOf).collect(Collectors.toList());

        List<OrderItemDO> subOrderItemList = orderItemSupport.listBySubOrderId(subOrderDO.getId());
        commentOrderItem(skuIds, subOrderItemList);
        // 判断是否该子订单的订单项是否全部都已评价
        List<OrderItemDO> subItemList = orderItemSupport.listBySubOrderId(subOrderDO.getOrderId());
        Optional<OrderItemDO> subItem = subItemList.stream()
            .filter(orderItemDO -> YNEnum.N.getCode().equals(orderItemDO.getCommentStatus()))
            .findAny();
        if (!subItem.isPresent()) {
            subOrderDO.setCommentStatus(OrderCommentStatusEnum.ALL.getCode());
        }
        subOrderMapper.updateById(subOrderDO);
        // 判断是否该订单的订单项是否全部都已评价
        List<OrderItemDO> orderItemList = orderItemSupport.listByOrderId(subOrderDO.getOrderId());
        Optional<OrderItemDO> item = orderItemList.stream()
            .filter(orderItemDO -> YNEnum.N.getCode().equals(orderItemDO.getCommentStatus()))
            .findAny();
        if (!item.isPresent()) {
            orderDO.setCommentStatus(OrderCommentStatusEnum.ALL.getCode());
        }
        orderMapper.updateById(orderDO);
        // 调用pms评价接口
        List<SaveCommentRequestDTO> saveCommentRequestList =
            buildCommentRequest(subOrderId, skuIds, subOrderDO.getOrderId());
        BaseResult saveBaseResult = commentFeign.save(saveCommentRequestList);
        if (!saveBaseResult.getResult()) {
            throw new BusinessException(saveBaseResult.getCode(), saveBaseResult.getMsg());
        }
        SubOrderJobDO bySubOrderId = subOrderJobSupport.findBySubOrderId(subOrderId);
        if (bySubOrderId != null) {
            xxlJobSupport.deleteJob(bySubOrderId.getXxlJobId());
        }
        return ReturnT.SUCCESS;
    }

    private List<SaveCommentRequestDTO> buildCommentRequest(Long subOrderId, List<Long> skuIds, Long orderId) {
        return skuIds.stream().map(skuId -> {
            SaveCommentRequestDTO saveCommentRequestDTO = new SaveCommentRequestDTO();
            saveCommentRequestDTO.setSkuId(skuId);
            saveCommentRequestDTO.setOrderId(orderId);
            saveCommentRequestDTO.setSubOrderId(subOrderId);
            saveCommentRequestDTO.setContent(DEFAULT_GOOD_COMMENT);
            saveCommentRequestDTO.setStar(CommentLevelEnum.FIVE.getCode());
            return saveCommentRequestDTO;
        }).collect(Collectors.toList());
    }

    /**
     * 对订单项进行评价
     */
    private void commentOrderItem(List<Long> skuIds, List<OrderItemDO> orderItemList) {
        List<OrderItemDO> waitCommentOrderItemList = orderItemList.stream()
            .filter(orderItem -> skuIds.contains(orderItem.getSkuId())).collect(Collectors.toList());
        for (OrderItemDO orderItemDO : waitCommentOrderItemList) {
            if (YNEnum.Y.getCode().equals(orderItemDO.getCommentStatus())) {
                return;
            }
            orderItemDO.setCommentStatus(YNEnum.Y.getCode());
            orderItemMapper.updateById(orderItemDO);
        }
    }

}
