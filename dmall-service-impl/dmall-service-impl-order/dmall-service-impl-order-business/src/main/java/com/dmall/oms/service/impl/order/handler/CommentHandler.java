package com.dmall.oms.service.impl.order.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.comment.CommentRequestDTO;
import com.dmall.oms.api.dto.comment.CommentSkuDTO;
import com.dmall.oms.api.enums.OrderCommentStatusEnum;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.api.enums.SplitEnum;
import com.dmall.oms.feign.CommentFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import com.dmall.oms.service.impl.support.OrderItemSupport;
import com.dmall.pms.api.dto.comment.request.SaveCommentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: 评价处理器
 * @author: created by hang.yu on 2020/4/12 15:55
 */
@Component
public class CommentHandler extends AbstractCommonHandler<CommentRequestDTO, SubOrderDO, Long> {

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private CommentFeign commentFeign;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public BaseResult<Long> processor(CommentRequestDTO requestDTO) {
        SubOrderDO subOrderDO = subOrderMapper.selectById(requestDTO.getSubOrderId());
        if (subOrderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        OrderDO orderDO = orderMapper.selectById(subOrderDO.getOrderId());
        if (orderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        // 已全部评价则不可评价
        if (OrderCommentStatusEnum.ALL.getCode().equals(orderDO.getCommentStatus())) {
            return ResultUtil.fail(OrderErrorEnum.COMMENT_ERROR);
        }
        if (OrderCommentStatusEnum.ALL.getCode().equals(subOrderDO.getCommentStatus())) {
            return ResultUtil.fail(OrderErrorEnum.COMMENT_ERROR);
        }

        // 子订单 评价状态默认是 部分评价
        subOrderDO.setCommentStatus(OrderCommentStatusEnum.PART.getCode());
        orderDO.setCommentStatus(OrderCommentStatusEnum.PART.getCode());
        List<Long> skuIds = requestDTO.getCommentSkuList().stream().map(CommentSkuDTO::getSkuId).collect(Collectors.toList());

        if (SplitEnum.NOT_NEED.getCode().equals(orderDO.getSplit())) {
            // 无需拆分
            List<OrderItemDO> orderItemList = orderItemSupport.listByOrderId(subOrderDO.getOrderId());
            // 对该订单的订单项进行评价
            commentOrderItem(skuIds, orderItemList);
        } else {
            List<OrderItemDO> orderItemList = orderItemSupport.listBySubOrderId(subOrderDO.getId());
            // 待评价列表
            commentOrderItem(skuIds, orderItemList);
            // 重新查询 判断是否该子订单的订单项是否全部都已评价
            List<OrderItemDO> itemList = orderItemSupport.listBySubOrderId(subOrderDO.getOrderId());
            Optional<OrderItemDO> item = itemList.stream()
                    .filter(orderItemDO -> YNEnum.N.getCode().equals(orderItemDO.getCommentStatus()))
                    .findAny();
            if (!item.isPresent()) {
                subOrderDO.setCommentStatus(OrderCommentStatusEnum.ALL.getCode());
            }
        }

        // 重新查询 判断是否该订单的订单项是否全部都已评价
        List<OrderItemDO> itemList = orderItemSupport.listByOrderId(subOrderDO.getOrderId());
        Optional<OrderItemDO> item = itemList.stream()
                .filter(orderItemDO -> YNEnum.N.getCode().equals(orderItemDO.getCommentStatus()))
                .findAny();
        if (!item.isPresent()) {
            subOrderDO.setCommentStatus(OrderCommentStatusEnum.ALL.getCode());
            orderDO.setCommentStatus(OrderCommentStatusEnum.ALL.getCode());
        }
        subOrderMapper.updateById(subOrderDO);
        orderMapper.updateById(orderDO);
        // 调用pms评价接口
        List<SaveCommentRequestDTO> saveCommentRequestList = buildCommentRequest(requestDTO, subOrderDO.getOrderId());
        BaseResult saveBaseResult = commentFeign.save(saveCommentRequestList);
        if (!saveBaseResult.getResult()) {
            throw new BusinessException(saveBaseResult.getCode(), saveBaseResult.getMsg());
        }
        return ResultUtil.success();
    }

    /**
     * 对订单项进行评价
     */
    private void commentOrderItem(List<Long> skuIds, List<OrderItemDO> orderItemList) {
        List<OrderItemDO> waitCommentOrderItemList = orderItemList.stream()
                .filter(orderItem -> skuIds.contains(orderItem.getSkuId())).collect(Collectors.toList());
        for (OrderItemDO orderItemDO : waitCommentOrderItemList) {
            if (YNEnum.Y.getCode().equals(orderItemDO.getCommentStatus())) {
                throw new BusinessException(OrderErrorEnum.COMMENT_ERROR);
            }
            orderItemDO.setCommentStatus(YNEnum.Y.getCode());
            orderItemMapper.updateById(orderItemDO);
        }
    }

    private List<SaveCommentRequestDTO> buildCommentRequest(CommentRequestDTO requestDTO, Long orderId) {
        return requestDTO.getCommentSkuList().stream().map(commentSkuDTO -> {
            SaveCommentRequestDTO saveCommentRequestDTO = new SaveCommentRequestDTO();
            saveCommentRequestDTO.setSkuId(commentSkuDTO.getSkuId());
            saveCommentRequestDTO.setOrderId(orderId);
            saveCommentRequestDTO.setSubOrderId(requestDTO.getSubOrderId());
            saveCommentRequestDTO.setContent(commentSkuDTO.getContent());
            saveCommentRequestDTO.setStar(commentSkuDTO.getStar());
            saveCommentRequestDTO.setMedias(commentSkuDTO.getMedias());
            return saveCommentRequestDTO;
        }).collect(Collectors.toList());
    }
}
