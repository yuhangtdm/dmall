package com.dmall.oms.service.impl.order.handler.buyer;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.comment.CommentRequestDTO;
import com.dmall.oms.api.dto.comment.CommentSkuDTO;
import com.dmall.oms.api.enums.OmsErrorEnum;
import com.dmall.oms.api.enums.OrderCommentStatusEnum;
import com.dmall.oms.feign.CommentFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import com.dmall.oms.service.support.OrderItemSupport;
import com.dmall.oms.service.validate.OmsValidate;
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

    @Autowired
    private OmsValidate omsValidate;

    @Override
    public BaseResult<Long> processor(CommentRequestDTO requestDTO) {
        SubOrderDO subOrderDO = omsValidate.validateSubOrder(requestDTO.getSubOrderId());
        OrderDO orderDO = omsValidate.validateOrder(subOrderDO.getOrderId());
        omsValidate.authentication(orderDO.getCreator());
        // 已全部评价则不可评价
        if (OrderCommentStatusEnum.ALL.getCode().equals(orderDO.getCommentStatus())) {
            return ResultUtil.fail(OmsErrorEnum.COMMENT_ERROR);
        }
        if (OrderCommentStatusEnum.ALL.getCode().equals(subOrderDO.getCommentStatus())) {
            return ResultUtil.fail(OmsErrorEnum.COMMENT_ERROR);
        }

        // 子订单 评价状态默认是 部分评价
        subOrderDO.setCommentStatus(OrderCommentStatusEnum.PART.getCode());
        orderDO.setCommentStatus(OrderCommentStatusEnum.PART.getCode());
        List<Long> skuIds =
            requestDTO.getCommentSkuList().stream().map(CommentSkuDTO::getSkuId).collect(Collectors.toList());

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
                throw new BusinessException(OmsErrorEnum.COMMENT_ERROR);
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
