package com.dmall.oms.service.impl.order;

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
import com.dmall.oms.api.service.OrderAfterSaleService;
import com.dmall.oms.service.impl.order.handler.aftersale.*;
import com.dmall.oms.service.impl.order.handler.seller.SellerReceiveHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @description: 订单售后服务实现
 * @author: created by hang.yu on 2020/4/18 12:46
 */
@RestController
public class OrderAfterSaleServiceImpl implements OrderAfterSaleService {

    @Autowired
    private ApplyRefundHandler applyRefundHandler;

    @Autowired
    private ApplyReturnHandler applyReturnHandler;

    @Autowired
    private AfterSalePageHandler afterSalePageHandler;

    @Autowired
    private AfterSaleDetailHandler afterSaleDetailHandler;

    @Autowired
    private AfterSaleApprovalHandler afterSaleApprovalHandler;

    @Autowired
    private WriteLogisticsNoHandler writeLogisticsNoHandler;

    @Autowired
    private SellerReceiveHandler sellerReceiveHandler;

    @Autowired
    private AfterSaleClosedHandler afterSaleClosedHandler;

    @Autowired
    private AfterSaleDeleteHandler afterSaleDeleteHandler;

    @Autowired
    private MyAfterSalePageHandler myAfterSalePageHandler;

    @Autowired
    private UploadApplyCredentialsHandler uploadApplyCredentialsHandler;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult applyRefund(@RequestBody OrderApplyRefundRequestDTO requestDTO) {
        return applyRefundHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> applyReturn(@RequestBody OrderApplyReturnRequestDTO requestDTO) {
        return applyReturnHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<UploadResult> uploadApplyCredentials(@NotNull(message = "上传售后凭证不能为空") MultipartFile file) {
        return uploadApplyCredentialsHandler.handler(file);
    }

    @Override
    public BaseResult<ResponsePage<AfterSalePageResponseDTO>> afterSalePage(@RequestBody AfterSalePageRequestDTO requestDTO) {
        return afterSalePageHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<AfterSaleDetailResponseDTO> afterSaleDetail(Long afterSaleId) {
        return afterSaleDetailHandler.handler(afterSaleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> afterSaleApproval(@RequestBody AfterSaleApprovalRequestDTO requestDTO) {
        return afterSaleApprovalHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> writeLogisticsNo(@RequestBody WriteLogisticsNoRequestDTO requestDTO) {
        return writeLogisticsNoHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> sellerReceive(Long afterSaleId) {
        return sellerReceiveHandler.handler(afterSaleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> afterSaleClosed(Long afterSaleId) {
        return afterSaleClosedHandler.handler(afterSaleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult afterSaleDelete(Long afterSaleId) {
        return afterSaleDeleteHandler.handler(afterSaleId);
    }

    @Override
    public BaseResult myAfterSalePage(@RequestBody MyAfterSalePageRequestDTO requestDTO) {
        return myAfterSalePageHandler.handler(requestDTO);
    }
}
