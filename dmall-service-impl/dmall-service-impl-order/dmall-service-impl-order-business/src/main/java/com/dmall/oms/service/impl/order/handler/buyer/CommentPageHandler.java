package com.dmall.oms.service.impl.order.handler.buyer;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.commentpage.CommentPageRequestDTO;
import com.dmall.oms.api.dto.commentpage.response.CommentPageResponseDTO;
import com.dmall.oms.api.dto.common.BuyerOrderItemDTO;
import com.dmall.oms.api.enums.SubOrderStatusEnum;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.service.impl.order.mapper.CommentMapper;
import com.dmall.oms.service.impl.order.mapper.dto.commentpage.CommentPageDbDTO;
import com.dmall.oms.service.impl.order.mapper.dto.commentpage.CommentPageRequestDbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 评价分页处理器
 * @author: created by hang.yu on 2020/4/11 21:20
 */
@Component
public class CommentPageHandler
    extends AbstractCommonHandler<CommentPageRequestDTO, SubOrderDO, CommentPageResponseDTO> {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseResult<ResponsePage<CommentPageResponseDTO>> processor(CommentPageRequestDTO requestDTO) {
        CommentPageRequestDbDTO commentPageRequestDbDTO = new CommentPageRequestDbDTO();
        commentPageRequestDbDTO.setCommentStatus(requestDTO.getCommentStatus());
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        commentPageRequestDbDTO.setCreator(portalMemberDTO.getId());
        Page page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        List<CommentPageDbDTO> result = commentMapper.commentPage(page, commentPageRequestDbDTO);
        List<CommentPageResponseDTO> collect = result.stream().map(commentPageDbDTO -> {
            CommentPageResponseDTO responseDTO = new CommentPageResponseDTO();
            responseDTO.setSubOrderId(commentPageDbDTO.getSubOrderId());
            responseDTO.setCommentStatus(EnumUtil.getCodeDescEnum(YNEnum.class, commentPageDbDTO.getCommentStatus()));
            responseDTO.setOrderTime(commentPageDbDTO.getOrderTime());
            responseDTO.setReceiverName(commentPageDbDTO.getReceiverName());
            List<BuyerOrderItemDTO> skuList = commentPageDbDTO.getSkuList().stream().map(commentSkuDbDTO -> {
                BuyerOrderItemDTO buyerOrderItemDTO = new BuyerOrderItemDTO();
                buyerOrderItemDTO.setSkuId(commentSkuDbDTO.getSkuId());
                buyerOrderItemDTO.setSkuName(commentSkuDbDTO.getSkuName());
                buyerOrderItemDTO.setSkuMainPic(commentSkuDbDTO.getSkuMainPic());
                buyerOrderItemDTO.setSkuNumber(commentSkuDbDTO.getSkuNumber());
                buyerOrderItemDTO.setSkuTotalPrice(commentSkuDbDTO.getSkuTotalPrice());
                buyerOrderItemDTO.setCanAfterSale(
                    SubOrderStatusEnum.COMPLETED.getCode().equals(commentPageDbDTO.getSubOrderStatus()));
                return buyerOrderItemDTO;
            }).collect(Collectors.toList());
            responseDTO.setSkuList(skuList);
            return responseDTO;
        }).collect(Collectors.toList());
        return ResultUtil.success(new ResponsePage(page.getTotal(), collect));
    }
}
