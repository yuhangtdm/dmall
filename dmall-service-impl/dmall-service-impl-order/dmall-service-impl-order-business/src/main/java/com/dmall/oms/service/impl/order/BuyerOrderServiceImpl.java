package com.dmall.oms.service.impl.order;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.dto.UploadResult;
import com.dmall.oms.api.dto.buyerdetail.BuyerOrderDetailResponseDTO;
import com.dmall.oms.api.dto.buyerorderpage.BuyerOrderPageRequestDTO;
import com.dmall.oms.api.dto.buyerorderpage.response.BuyerOrderPageResponseDTO;
import com.dmall.oms.api.dto.comment.CommentRequestDTO;
import com.dmall.oms.api.dto.commentpage.CommentPageRequestDTO;
import com.dmall.oms.api.dto.commentpage.response.CommentPageResponseDTO;
import com.dmall.oms.api.dto.createorder.CreateOrderRequestDTO;
import com.dmall.oms.api.dto.tocomment.ToCommentResponseDTO;
import com.dmall.oms.api.dto.totrade.request.ToTradeRequestDTO;
import com.dmall.oms.api.dto.totrade.response.ToTradeResponseDTO;
import com.dmall.oms.api.service.BuyerOrderService;
import com.dmall.oms.service.impl.order.handler.buyer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @description: 买家端订单服务
 * @author: created by hang.yu on 2020/4/18 12:35
 */
@RestController
public class BuyerOrderServiceImpl implements BuyerOrderService {

    @Autowired
    private ToTradeHandler toTradeHandler;

    @Autowired
    private CreateOrderHandler createOrderHandler;

    @Autowired
    private CancelOrderHandler cancelOrderHandler;

    @Autowired
    private DeleteOrderHandler deleteOrderHandler;

    @Autowired
    private BuyerOrderDetailHandler buyerOrderDetailHandler;

    @Autowired
    private BuyerOrderPageHandler buyerOrderPageHandler;

    @Autowired
    private ReceiveHandler receiveHandler;

    @Autowired
    private ToCommentHandler toCommentHandler;

    @Autowired
    private CommentHandler commentHandler;

    @Autowired
    private CommentPageHandler commentPageHandler;

    @Autowired
    private UploadCommentPicHandler uploadCommentPicHandler;

    @Override
    public BaseResult<ToTradeResponseDTO> toTrade(@RequestBody ToTradeRequestDTO requestDTO) {
        return toTradeHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<String> createOrder(@RequestBody CreateOrderRequestDTO requestDTO) {
        return createOrderHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> cancelOrder(Long orderId) {
        return cancelOrderHandler.handler(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> deleteOrder(Long orderId) {
        return deleteOrderHandler.handler(orderId);
    }

    @Override
    public BaseResult<BuyerOrderDetailResponseDTO> buyerDetail(Long subOrderId) {
        return buyerOrderDetailHandler.handler(subOrderId);
    }

    @Override
    public BaseResult<ResponsePage<BuyerOrderPageResponseDTO>>
        sellerOrderPage(@RequestBody BuyerOrderPageRequestDTO requestDTO) {
        return buyerOrderPageHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> receive(Long subOrderId) {
        return receiveHandler.handler(subOrderId);
    }

    @Override
    public BaseResult<ToCommentResponseDTO> toComment(Long subOrderId) {
        return toCommentHandler.handler(subOrderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult comment(@RequestBody CommentRequestDTO requestDTO) {
        return commentHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<UploadResult> uploadCommentPic(MultipartFile file) {
        return uploadCommentPicHandler.handler(file);
    }

    @Override
    public BaseResult<ResponsePage<CommentPageResponseDTO>> commentPage(@Valid CommentPageRequestDTO requestDTO) {
        return commentPageHandler.handler(requestDTO);
    }

}
