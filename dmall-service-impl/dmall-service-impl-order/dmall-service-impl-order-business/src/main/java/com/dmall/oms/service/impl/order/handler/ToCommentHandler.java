package com.dmall.oms.service.impl.order.handler;

import com.dmall.common.util.ResultUtil;
import com.dmall.oms.api.dto.commentpage.response.CommentSkuDTO;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.tocomment.ToCommentResponseDTO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.service.impl.order.mapper.CommentMapper;
import com.dmall.oms.service.impl.order.mapper.dto.tocomment.ToCommentDbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 评价页处理器
 * @author: created by hang.yu on 2020/4/11 22:26
 */
@Component
public class ToCommentHandler extends AbstractCommonHandler<Long, SubOrderDO, ToCommentResponseDTO> {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseResult<ToCommentResponseDTO> processor(Long subOrderId) {
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        ToCommentDbDTO toCommentDbDTO = commentMapper.toComment(subOrderId, portalMemberDTO.getId());
        if (toCommentDbDTO == null) {
            return ResultUtil.fail(OrderErrorEnum.NOT_COMMENT);
        }
        ToCommentResponseDTO responseDTO = new ToCommentResponseDTO();
        responseDTO.setSubOrderId(toCommentDbDTO.getSubOrderId());
        responseDTO.setOrderTime(toCommentDbDTO.getOrderTime());
        List<CommentSkuDTO> skuList = toCommentDbDTO.getSkuList().stream().map(commentSkuDbDTO -> {
            CommentSkuDTO commentSkuDTO = new CommentSkuDTO();
            commentSkuDTO.setSkuId(commentSkuDbDTO.getSkuId());
            commentSkuDTO.setSkuName(commentSkuDbDTO.getSkuName());
            commentSkuDTO.setSkuMainPic(commentSkuDbDTO.getSkuMainPic());
            commentSkuDTO.setSkuNumber(commentSkuDbDTO.getSkuNumber());
            commentSkuDTO.setSkuTotalPrice(commentSkuDbDTO.getSkuTotalPrice());
            return commentSkuDTO;
        }).collect(Collectors.toList());
        responseDTO.setSkuList(skuList);
        return ResultUtil.success(responseDTO);
    }
}
