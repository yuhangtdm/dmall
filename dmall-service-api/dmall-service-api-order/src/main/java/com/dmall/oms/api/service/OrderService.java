package com.dmall.oms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.oms.api.dto.aftersaleapproval.AfterSaleApprovalRequestDTO;
import com.dmall.oms.api.dto.aftersaledetail.AfterSaleDetailResponseDTO;
import com.dmall.oms.api.dto.aftersalepage.AfterSalePageRequestDTO;
import com.dmall.oms.api.dto.aftersalepage.AfterSalePageResponseDTO;
import com.dmall.oms.api.dto.applyrefund.OrderApplyRefundRequestDTO;
import com.dmall.oms.api.dto.applyreturn.OrderApplyReturnRequestDTO;
import com.dmall.oms.api.dto.buyerdetail.BuyerOrderDetailResponseDTO;
import com.dmall.oms.api.dto.buyerorderpage.BuyerOrderPageRequestDTO;
import com.dmall.oms.api.dto.buyerorderpage.response.BuyerOrderPageResponseDTO;
import com.dmall.oms.api.dto.comment.CommentRequestDTO;
import com.dmall.oms.api.dto.commentdetail.CommentDetailResponseDTO;
import com.dmall.oms.api.dto.commentpage.CommentPageRequestDTO;
import com.dmall.oms.api.dto.commentpage.response.CommentPageResponseDTO;
import com.dmall.oms.api.dto.createorder.CreateOrderRequestDTO;
import com.dmall.oms.api.dto.deliver.DeliverRequestDTO;
import com.dmall.oms.api.dto.deliverpage.DeliverOrderPageRequestDTO;
import com.dmall.oms.api.dto.deliverpage.DeliverOrderPageResponseDTO;
import com.dmall.oms.api.dto.demolitionorder.DemolitionOrderRequestDTO;
import com.dmall.oms.api.dto.demolitionorderpage.DemolitionOrderPageRequestDTO;
import com.dmall.oms.api.dto.demolitionorderpage.DemolitionOrderPageResponseDTO;
import com.dmall.oms.api.dto.items.OrderItemListResponseDTO;
import com.dmall.oms.api.dto.sellerdetail.SellerOrderDetailResponseDTO;
import com.dmall.oms.api.dto.tocomment.ToCommentResponseDTO;
import com.dmall.oms.api.dto.totrade.request.ToTradeRequestDTO;
import com.dmall.oms.api.dto.totrade.response.ToTradeResponseDTO;
import com.dmall.oms.api.dto.writelogisticsno.WriteLogisticsNoRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 订单服务
 * @author: created by hang.yu on 2020/3/26 22:29
 */
@Api(tags = "订单服务")
@RequestMapping("/order")
public interface OrderService {

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

    @GetMapping("/demolitionOrderPage")
    @ApiOperation(value = "拆单分页")
    BaseResult<ResponsePage<DemolitionOrderPageResponseDTO>> demolitionOrderPage(@RequestBody @Valid DemolitionOrderPageRequestDTO requestDTO);

    @GetMapping("/demolitionOrder")
    @ApiOperation(value = "拆单")
    BaseResult<Long> demolitionOrder(@RequestBody @Valid DemolitionOrderRequestDTO requestDTO);

    @GetMapping("/items/{orderId}")
    @ApiOperation(value = "根据订单号查询订单明细")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    BaseResult<List<OrderItemListResponseDTO>> items(@PathVariable("orderId") Long orderId);

    @PostMapping("/deliverOrderPage")
    @ApiOperation(value = "待发货分页")
    BaseResult<ResponsePage<DeliverOrderPageResponseDTO>> deliverOrderPage(@RequestBody @Valid DeliverOrderPageRequestDTO requestDTO);

    @PostMapping("/deliver")
    @ApiOperation(value = "发货")
    BaseResult<Long> deliver(@RequestBody @Valid DeliverRequestDTO requestDTO);

    @GetMapping("/detail/{subOrderId}")
    @ApiImplicitParam(name = "subOrderId", value = "子订单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "买家端订单详情")
    BaseResult<BuyerOrderDetailResponseDTO> buyerDetail(@PathVariable("subOrderId") Long subOrderId);

    @GetMapping("/seller/detail/{orderId}")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "卖家端订单详情")
    BaseResult<SellerOrderDetailResponseDTO> sellerDetail(@PathVariable("orderId") Long orderId);

    @PostMapping("/order/page")
    @ApiOperation(value = "买家端订单列表")
    BaseResult<ResponsePage<BuyerOrderPageResponseDTO>> sellerOrderPage(@RequestBody @Valid BuyerOrderPageRequestDTO requestDTO);

    @GetMapping("/receive/{subOrderId}")
    @ApiImplicitParam(name = "subOrderId", value = "子订单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "确认收货")
    BaseResult<Long> receive(@PathVariable("subOrderId") Long subOrderId);

    @PostMapping("/commentPage")
    @ApiOperation(value = "评价分页")
    BaseResult<ResponsePage<CommentPageResponseDTO>> commentPage(@RequestBody @Valid CommentPageRequestDTO requestDTO);

    @GetMapping("/toComment/{subOrderId}")
    @ApiImplicitParam(name = "subOrderId", value = "子订单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "评价页面")
    BaseResult<ToCommentResponseDTO> toComment(@PathVariable("subOrderId") Long subOrderId);

    @PostMapping("/comment")
    @ApiOperation(value = "评价")
    BaseResult comment(@RequestBody @Valid CommentRequestDTO requestDTO);

    @GetMapping("/commentDetail/{subOrderId}")
    @ApiImplicitParam(name = "subOrderId", value = "子订单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "评价详情")
    BaseResult<List<CommentDetailResponseDTO>> commentDetail(@PathVariable("subOrderId") Long subOrderId);

    @PostMapping("/applyRefund")
    @ApiOperation(value = "申请退款")
    BaseResult<Long> applyRefund(@RequestBody @Valid OrderApplyRefundRequestDTO requestDTO);

    @PostMapping("/applyReturn")
    @ApiOperation(value = "退货退款")
    BaseResult<Long> applyReturn(@RequestBody @Valid OrderApplyReturnRequestDTO requestDTO);

    @PostMapping("/afterSalePage")
    @ApiOperation(value = "售后分页")
    BaseResult<ResponsePage<AfterSalePageResponseDTO>> afterSalePage(@RequestBody @Valid AfterSalePageRequestDTO requestDTO);

    @GetMapping("/afterSaleDetail/{afterSaleId}")
    @ApiImplicitParam(name = "afterSaleId", value = "售后单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "售后单详情")
    BaseResult<AfterSaleDetailResponseDTO> afterSaleDetail(@PathVariable("afterSaleId") Long afterSaleId);

    @PostMapping("/afterSaleApproval")
    @ApiOperation(value = "售后审核")
    BaseResult<Long> afterSaleApproval(@RequestBody @Valid AfterSaleApprovalRequestDTO requestDTO);

    // 上传物流单号
    @PostMapping("/writeLogisticsNo")
    @ApiOperation(value = "填写物流单号")
    BaseResult<Long> writeLogisticsNo(@RequestBody @Valid WriteLogisticsNoRequestDTO requestDTO);

    @GetMapping("/sellerReceive/{afterSaleId}")
    @ApiOperation(value = "卖家确认收货")
    @ApiImplicitParam(name = "afterSaleId", value = "售后单号", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> sellerReceive(@PathVariable("afterSaleId") Long afterSaleId);

    @GetMapping("/afterSaleClosed/{afterSaleId}")
    @ApiOperation(value = "关闭售后单")
    @ApiImplicitParam(name = "afterSaleId", value = "售后单号", required = true, dataType = "int", paramType = "path")
    BaseResult afterSaleClosed(@PathVariable("afterSaleId") Long afterSaleId);

    @GetMapping("/afterSaleDelete/{afterSaleId}")
    @ApiOperation(value = "删除售后单")
    @ApiImplicitParam(name = "afterSaleId", value = "售后单号", required = true, dataType = "int", paramType = "path")
    BaseResult afterSaleDelete(@PathVariable("afterSaleId") Long afterSaleId);

    //TODO 发货后 15天 自动确认收货

    // TODO 确认收货后 15天 自动好评

    // TODO 上传图片

    // TODO 操作的横向越权问题

    // TODO 售后日志记录

    // TODO 卖家端订单详情、卖家端订单列表、买家端订单详情 应包含售后信息
}
