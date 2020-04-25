package com.dmall.oms.service.impl.order.handler.buyer;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.commentdetail.CommentDetailResponseDTO;
import com.dmall.oms.api.enums.OmsErrorEnum;
import com.dmall.oms.api.enums.OrderCommentStatusEnum;
import com.dmall.oms.api.enums.SplitEnum;
import com.dmall.oms.feign.CommentFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.service.support.OrderItemSupport;
import com.dmall.oms.service.validate.OmsValidate;
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
    private CommentFeign commentFeign;

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Autowired
    private OmsValidate omsValidate;

    @Override
    public BaseResult<List<CommentDetailResponseDTO>> processor(Long subOrderId) {
        SubOrderDO subOrderDO = omsValidate.validateSubOrder(subOrderId);
        OrderDO orderDO = omsValidate.validateOrder(subOrderDO.getOrderId());
        omsValidate.authentication(orderDO.getCreator());
        if (OrderCommentStatusEnum.NO.getCode().equals(subOrderDO.getCommentStatus())) {
            return ResultUtil.fail(OmsErrorEnum.COMMENT_DETAIL_ERROR);
        }
        // 调用pms查看评价列表
        BaseResult<List<CommentResponseDTO>> baseResult = commentFeign.list(subOrderId);
        if (!baseResult.getResult()) {
            return ResultUtil.fail(baseResult.getCode(), baseResult.getMsg());
        }
        List<CommentResponseDTO> data = baseResult.getData();
        List<OrderItemDO> orderItemList;
        if (SplitEnum.NOT_NEED.getCode().equals(orderDO.getSplit())) {
            // 无需拆单
            orderItemList = orderItemSupport.listByOrderId(subOrderDO.getOrderId());
        } else {
            // 已拆单
            orderItemList = orderItemSupport.listBySubOrderId(subOrderDO.getOrderId());
        }
        return buildResult(data, orderItemList);
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
