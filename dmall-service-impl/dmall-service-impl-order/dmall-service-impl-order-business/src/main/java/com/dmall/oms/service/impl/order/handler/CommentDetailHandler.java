package com.dmall.oms.service.impl.order.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.commentdetail.CommentDetailResponseDTO;
import com.dmall.oms.api.enums.OrderCommentStatusEnum;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.api.enums.SplitEnum;
import com.dmall.oms.feign.CommentFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import com.dmall.oms.service.impl.support.OrderItemSupport;
import com.dmall.pms.api.dto.comment.response.CommentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: 评价详情处理器
 * @author: created by hang.yu on 2020/4/12 16:51
 */
@Component
public class CommentDetailHandler extends AbstractCommonHandler<Long, SubOrderDO, List<CommentDetailResponseDTO>> {

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CommentFeign commentFeign;

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Override
    public BaseResult<List<CommentDetailResponseDTO>> processor(Long subOrderId) {
        SubOrderDO subOrderDO = subOrderMapper.selectById(subOrderId);
        if (subOrderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        OrderDO orderDO = orderMapper.selectById(subOrderDO.getId());
        if (orderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        if (OrderCommentStatusEnum.NO.getCode().equals(subOrderDO.getCommentStatus())) {
            return ResultUtil.fail(OrderErrorEnum.COMMENT_DETAIL_ERROR);
        }
        // 调用pms查看评价列表
        BaseResult<List<CommentResponseDTO>> baseResult = commentFeign.list(subOrderId);
        if (!baseResult.getResult()) {
            return ResultUtil.fail(baseResult.getCode(), baseResult.getMsg());
        }
        List<CommentResponseDTO> data = baseResult.getData();
        if (SplitEnum.NOT_NEED.getCode().equals(orderDO.getSplit())) {
            // 无需拆单
            List<OrderItemDO> orderItemList = orderItemSupport.listByOrderId(subOrderDO.getOrderId());
            return buildResult(data, orderItemList);
        } else {
            // 已拆单
            List<OrderItemDO> orderItemList = orderItemSupport.listBySubOrderId(subOrderDO.getOrderId());
            return buildResult(data, orderItemList);
        }
    }

    /**
     * 构建结果
     */
    private BaseResult<List<CommentDetailResponseDTO>> buildResult(List<CommentResponseDTO> data, List<OrderItemDO> orderItemList) {
        List<CommentDetailResponseDTO> collect = orderItemList.stream().map(orderItemDO -> {
            Optional<CommentResponseDTO> any = data.stream()
                    .filter(commentResponseDTO -> orderItemDO.getSkuId().equals(commentResponseDTO.getSkuId()))
                    .findAny();
            CommentDetailResponseDTO responseDTO = new CommentDetailResponseDTO();
            responseDTO.setSkuId(orderItemDO.getSkuId());
            responseDTO.setSkuName(orderItemDO.getSkuName());
            responseDTO.setSkuMainPic(orderItemDO.getSkuPic());
            responseDTO.setSkuNumber(orderItemDO.getSkuNumber());
            responseDTO.setSkuTotalPrice(orderItemDO.getSkuTotalPrice());
            if (any.isPresent()) {
                CommentResponseDTO commentResponseDTO = any.get();
                responseDTO.setStar(commentResponseDTO.getStar());
                responseDTO.setContent(commentResponseDTO.getContent());
                responseDTO.setCreateTime(commentResponseDTO.getCreateTime());
            }
            return responseDTO;
        }).collect(Collectors.toList());
        return ResultUtil.success(collect);
    }
}