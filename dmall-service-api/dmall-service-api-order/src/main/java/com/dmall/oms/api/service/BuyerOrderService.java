package com.dmall.oms.api.service;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @description: 买家端订单服务
 * @author: created by hang.yu on 2020/4/18 12:33
 */
@Api(tags = "买家端订单服务")
@Validated
@RequestMapping("/buyer/order")
public interface BuyerOrderService {

    @PostMapping("/toTrade")
    @ApiOperation(value = "跳转结算页")
    BaseResult<ToTradeResponseDTO> toTrade(@RequestBody @Valid ToTradeRequestDTO requestDTO);

    @PostMapping("/createOrder")
    @ApiOperation(value = "创建订单")
    BaseResult<String> createOrder(@RequestBody @Valid CreateOrderRequestDTO requestDTO);

    @GetMapping("/cancel/{orderId}")
    @ApiOperation(value = "取消订单")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> cancelOrder(@PathVariable("orderId") Long orderId);

    @DeleteMapping("/delete/{orderId}")
    @ApiOperation(value = "删除订单")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> deleteOrder(@PathVariable("orderId") Long orderId);

    @PostMapping("/page")
    @ApiOperation(value = "买家端订单列表")
    BaseResult<ResponsePage<BuyerOrderPageResponseDTO>> sellerOrderPage(@RequestBody @Valid BuyerOrderPageRequestDTO requestDTO);

    @GetMapping("/detail/{subOrderId}")
    @ApiImplicitParam(name = "subOrderId", value = "子订单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "买家端订单详情")
    BaseResult<BuyerOrderDetailResponseDTO> buyerDetail(@PathVariable("subOrderId") Long subOrderId);

    @GetMapping("/receive/{subOrderId}")
    @ApiImplicitParam(name = "subOrderId", value = "子订单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "确认收货")
    BaseResult<Long> receive(@PathVariable("subOrderId") Long subOrderId);

    @GetMapping("/toComment/{subOrderId}")
    @ApiImplicitParam(name = "subOrderId", value = "子订单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "跳转评价页面")
    BaseResult<ToCommentResponseDTO> toComment(@PathVariable("subOrderId") Long subOrderId);

    @PostMapping("/comment")
    @ApiOperation(value = "评价")
    BaseResult comment(@RequestBody @Valid CommentRequestDTO requestDTO);

    @ApiOperation(value = "上传评价图片")
    @PostMapping("/uploadLogo")
    BaseResult<UploadResult> uploadCommentPic(@NotNull(message = "评价图片不能为空") MultipartFile file);

    @PostMapping("/commentPage")
    @ApiOperation(value = "评价分页")
    BaseResult<ResponsePage<CommentPageResponseDTO>> commentPage(@RequestBody @Valid CommentPageRequestDTO requestDTO);


}
