package com.dmall.oms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.dto.UploadResult;
import com.dmall.oms.api.dto.aftersaleapproval.AfterSaleApprovalRequestDTO;
import com.dmall.oms.api.dto.aftersaledetail.AfterSaleDetailResponseDTO;
import com.dmall.oms.api.dto.aftersalepage.AfterSalePageRequestDTO;
import com.dmall.oms.api.dto.aftersalepage.AfterSalePageResponseDTO;
import com.dmall.oms.api.dto.applyrefund.OrderApplyRefundRequestDTO;
import com.dmall.oms.api.dto.applyreturn.OrderApplyReturnRequestDTO;
import com.dmall.oms.api.dto.myaftersalepage.MyAfterSalePageRequestDTO;
import com.dmall.oms.api.dto.writelogisticsno.WriteLogisticsNoRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @description: 订单售后服务
 * @author: created by hang.yu on 2020/4/18 12:41
 */
@Api(tags = "订单售后服务")
@Validated
@RequestMapping("/afterSale")
public interface OrderAfterSaleService {

    @PostMapping("/applyRefund")
    @ApiOperation(value = "买家申请退款")
    BaseResult<Long> applyRefund(@RequestBody @Valid OrderApplyRefundRequestDTO requestDTO);

    @PostMapping("/applyReturn")
    @ApiOperation(value = "买家退货退款")
    BaseResult<Long> applyReturn(@RequestBody @Valid OrderApplyReturnRequestDTO requestDTO);

    @ApiOperation(value = "上传售后凭证")
    @PostMapping("/uploadLogo")
    BaseResult<UploadResult> uploadApplyCredentials(@NotNull(message = "上传售后凭证不能为空") MultipartFile file);

    @PostMapping("/seller/age")
    @ApiOperation(value = "卖家售后分页")
    BaseResult<ResponsePage<AfterSalePageResponseDTO>> afterSalePage(@RequestBody @Valid AfterSalePageRequestDTO requestDTO);

    @GetMapping("/detail/{afterSaleId}")
    @ApiImplicitParam(name = "afterSaleId", value = "售后单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "卖家售后单详情")
    BaseResult<AfterSaleDetailResponseDTO> afterSaleDetail(@PathVariable("afterSaleId") Long afterSaleId);

    @PostMapping("/approval")
    @ApiOperation(value = "卖家售后审核")
    BaseResult<Long> afterSaleApproval(@RequestBody @Valid AfterSaleApprovalRequestDTO requestDTO);

    // 上传物流单号
    @PostMapping("/writeLogisticsNo")
    @ApiOperation(value = "买家填写物流单号")
    BaseResult<Long> writeLogisticsNo(@RequestBody @Valid WriteLogisticsNoRequestDTO requestDTO);

    @GetMapping("/receive/{afterSaleId}")
    @ApiOperation(value = "卖家确认收货")
    @ApiImplicitParam(name = "afterSaleId", value = "售后单号", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> sellerReceive(@PathVariable("afterSaleId") Long afterSaleId);

    @GetMapping("/close/{afterSaleId}")
    @ApiOperation(value = "买家关闭售后单")
    @ApiImplicitParam(name = "afterSaleId", value = "售后单号", required = true, dataType = "int", paramType = "path")
    BaseResult afterSaleClosed(@PathVariable("afterSaleId") Long afterSaleId);

    @GetMapping("/delete/{afterSaleId}")
    @ApiOperation(value = "买家删除售后单")
    @ApiImplicitParam(name = "afterSaleId", value = "售后单号", required = true, dataType = "int", paramType = "path")
    BaseResult afterSaleDelete(@PathVariable("afterSaleId") Long afterSaleId);

    @PostMapping("/buyer/Page")
    @ApiOperation(value = "买家售后单分页")
    BaseResult myAfterSalePage(@RequestBody @Valid MyAfterSalePageRequestDTO requestDTO);

}
